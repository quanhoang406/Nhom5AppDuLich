package com.nhom5.appdulich.ui.fragment.detail_place

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.core.room.FavoritePlace
import com.nhom5.appdulich.data.model.Place
import com.nhom5.appdulich.databinding.FragmentDetailPlaceBinding
import com.nhom5.appdulich.extension.setUpToolbar
import com.nhom5.appdulich.ui.adapter.map.AdapterImage
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FragmentDetailPlace : BaseFragment<FragmentDetailPlaceBinding>() {
    private var _id: Int? = null
    val _viewModel by viewModels<DetailViewModel>()

    @Inject
    lateinit var adapterImage: AdapterImage
    private var _place: Place? = null

    override fun getViewBinding() = FragmentDetailPlaceBinding.inflate(layoutInflater)

    override fun listenerViewModel() {
        _viewModel.loadingDialog = {
            helpers.showProgressLoading(requireContext())
        }

        _viewModel.showError = {
            helpers.dismissProgress()
            helpers.showAlertDialog(msg = it, context = requireContext())
        }

        _id?.let {
            _viewModel.getDataPlaceIdPlace(it) { detailResponse ->
                helpers.dismissProgress()
                detailResponse.data?.let { place ->
                    _place = place[0]
                    binding.place = _place
                    _place?.arrayImageView?.split("@")?.also {
                        adapterImage.updateItems(it.toMutableList())
                    }
                }
            }
        }
    }

    override fun onInit() {
        initView()
        onClickView()
    }

    private fun onClickView() {
        binding.imgLike.setOnClickListener {
            _place?.let {
                _viewModel.insertPlace(FavoritePlace(it.id, it.name, it.introduce, it.image))
                helpers.showToast(getString(R.string.lbl_save_local_success))
            }
        }

        binding.imgEvaluate.setOnClickListener {
            _place?.let {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.fragmentDetailPlace, true).build()
                findNavController().navigate(R.id.fragmentMap, bundleOf(Const.KEY_PLACE to it),navOptions)
            }
        }
    }

    private fun initView() {
        binding.lifecycleOwner = this
        arguments?.apply {
            _id = getInt(Const.KEY_ID)
        }

        binding.toolbar.toolbar.setUpToolbar {
            requireActivity().onBackPressed()
        }

        binding.recyclerImage.adapter = adapterImage
    }
}