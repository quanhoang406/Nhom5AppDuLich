package com.nhom5.appdulich.ui.fragment.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.tasks.CancellationTokenSource
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.data.model.Place
import com.nhom5.appdulich.databinding.FragmentMapBinding
import com.nhom5.appdulich.ui.adapter.map.MenuAdapter
import com.nhom5.appdulich.ui.dialog.ShowButtonSheetDialogMap
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import com.nhom5.appdulich.R
import javax.inject.Inject


private const val TAG = "MAP"

@AndroidEntryPoint
@SuppressLint("MissingPermission")
class FragmentMap : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback,
    SearchView.OnQueryTextListener {
    @Inject
    lateinit var adapterMenu: MenuAdapter

    private val _viewModel by viewModels<MapViewModel>()
    private lateinit var _fusedLocationClient: FusedLocationProviderClient

    private val _resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            checkGps()
        }

    private val _requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                checkGps()
            } else {
                helpers.showAlertDialog(
                    msg = getString(R.string.lbl_permission),
                    context = requireContext()
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun getViewBinding() = FragmentMapBinding.inflate(layoutInflater)

    override fun listenerViewModel() {
        _viewModel.showError = {
            helpers.showAlertDialog(msg = it, context = requireContext())
            helpers.dismissProgress()
        }

        _viewModel.loadingDialog = {
            helpers.showProgressLoading(requireContext())
        }

        _viewModel.getMenuAll {
            adapterMenu.updateItems(it.toMutableList())
            helpers.dismissProgress()
        }
    }

    override fun onInit() {
        initView()
        initMap()
        checkPermission()
        onClickView()
    }

    private fun onClickView() {
        binding.imgLocation.setOnClickListener {
            checkGps()
        }
    }

    private fun initView() {
        binding.searchView.maxWidth = Int.MAX_VALUE

        binding.recyclerMenu.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterMenu
            adapterMenu.listener = { _, item, _ ->
                item.id?.let {
                    _viewModel.getDataPlaceFromIdMenu(it) {
                        helpers.dismissProgress()
                    }
                }
            }
        }

        binding.searchView.setOnQueryTextListener(this)
    }

    // check permission find location
    private fun checkPermission() {
        Log.d(TAG, "checkPermission: ")
        if (_viewModel.isPermissionGrand(permissions)) {
            checkGps()
            return
        }
        _requestMultiplePermissions.launch(permissions)
    }

    // check  gps
    private fun checkGps() {
        Log.d(TAG, "checkGps: ")
        if (_viewModel.isGpsStatus()) {
            findLocation()
            return
        }
        receivePlace()
        helpers.showAlertDialog(
            msg = getString(R.string.lbl_location_error),
            context = requireContext(),
            onClick = {
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                    _resultLauncher.launch(this)
                }
            }
        )
    }

    private fun findLocation() {
        Log.d(TAG, "findLocation: ")
        _fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            if (task.result != null) {
                _viewModel.findLocation(task.result)
                lifecycleScope.launch {
                    delay(400)
                    receivePlace()
                }
                return@addOnCompleteListener
            }

            helpers.showProgressLoading(requireContext())
            _fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_NO_POWER,
                CancellationTokenSource().token
            ).addOnSuccessListener {
                helpers.dismissProgress()
                _viewModel.findLocation(it)
                receivePlace()
            }
        }
    }

    private fun initMap() {
        Log.d(TAG, "initMap: ")
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun receivePlace(){
        (arguments?.getSerializable(Const.KEY_PLACE) as? Place)?.let {
            _viewModel.map?.clear()
            _viewModel.addMarker(it)
            _viewModel.moveCamera(it)
        }
    }
    override fun onMapReady(p0: GoogleMap) {
        Log.d(TAG, "onMapReady: ")
        _viewModel.map = p0

        p0.setOnMarkerClickListener { marker ->
            _viewModel.getPlaceFromName(marker.title ?: "") {
                val dialog = ShowButtonSheetDialogMap().apply {
                    arguments = Bundle().apply {
                        putSerializable(Const.KEY_PLACE, it)
                    }
                }
                helpers.dismissProgress()
                dialog.show(requireActivity().supportFragmentManager, Const.TAG_DIALOG)
            }
            return@setOnMarkerClickListener false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        _viewModel.searchPlace(query.toString()) {
            helpers.dismissProgress()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}