package com.nhom5.appdulich.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nhom5.appdulich.R
import com.nhom5.appdulich.data.model.Place
import com.nhom5.appdulich.databinding.DialogMapBinding
import com.nhom5.appdulich.ui.adapter.map.AdapterImage
import com.nhom5.appdulich.ui.fragment.bottom_navigation.BottomNavigation
import com.nhom5.appdulich.utils.Const

class ShowButtonSheetDialogMap : BottomSheetDialogFragment() {
    private lateinit var _binding: DialogMapBinding
    private val _adapterImage by lazy {  AdapterImage() }
    private var _place : Place? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogMapBinding.inflate(layoutInflater)
        _binding.lifecycleOwner = this
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
        onClickView()
    }

    private fun onClickView() {
        _binding.btnDirection.setOnClickListener {
            _place?.let {
                BottomNavigation.navController.navigate(R.id.action_global_fragmentDetailPlace,Bundle().apply {
                    putInt(Const.KEY_ID,it.id)
                })
            }
        }
    }

    private fun loadData() {
        arguments?.apply {
            _place = getSerializable(Const.KEY_PLACE) as Place?
            _place?.let {
                _binding.place = it
                val list = it.arrayImageView?.split("@")
                list?.let { list -> _adapterImage.updateItems(list.toMutableList()) }
            }
        }
    }

    private fun initView() {
        _binding.recyclerImage.adapter = _adapterImage
    }
}