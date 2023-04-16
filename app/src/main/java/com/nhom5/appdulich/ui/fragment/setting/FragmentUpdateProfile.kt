package com.nhom5.appdulich.ui.fragment.setting

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentUpdateProfileBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.viewmodel.SettingAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentUpdateProfile : BaseFragment<FragmentUpdateProfileBinding>(), View.OnClickListener {
    private val _viewModel by activityViewModels<SettingAccountViewModel>()

    override fun getViewBinding() = FragmentUpdateProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this
    }

    override fun listenerViewModel() {
        _viewModel.showError = {
            helpers.showAlertDialog(msg = it, context = requireContext())
            helpers.dismissProgress()
        }

        _viewModel.loadingDialog = {
            helpers.showProgressLoading(requireContext())
        }
    }

    override fun onInit() {
        onClickView()
    }

    private fun onClickView() {
        binding.imgDatePicker.setOnClickListener(this)
        binding.imgDrop.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgDatePicker -> helpers.showDatePickerDialog(requireContext()) {
                _viewModel.setBirthDay = it
            }
            R.id.btnUpdate -> _viewModel.updateProfile {
                helpers.dismissProgress()
                requireView().navigate(R.id.action_fragmentUpdateProfile_to_fragmentMyProfile)
            }
            R.id.imgDrop -> {
                PopupMenu(requireContext(), binding.imgDrop).apply {
                    inflate(R.menu.menu_gender)
                    setOnMenuItemClickListener {
                        _viewModel.setGender = when (it.itemId) {
                            R.id.itemBoy -> getString(R.string.lbl_boy)
                            else -> getString(R.string.lbl_gir)
                        }
                        true
                    }
                    show()
                }
            }
            R.id.imgBack -> requireActivity().onBackPressed()
        }
    }
}