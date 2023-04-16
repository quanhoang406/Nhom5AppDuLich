package com.nhom5.appdulich.ui.fragment.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentProfileBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.viewmodel.SettingAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMyProfile : BaseFragment<FragmentProfileBinding>(), View.OnClickListener {
    private val _viewModel by activityViewModels<SettingAccountViewModel>()

    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this
    }

    override fun listenerViewModel() {
        _viewModel.showError = {
            helpers.showAlertDialog(msg = it, context = requireContext())
        }
    }

    override fun onInit() {
        onClickView()
    }

    private fun onClickView() {
        binding.txtUpdate.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtUpdate -> requireView().navigate(R.id.action_fragmentMyProfile_to_fragmentUpdateProfile)
            R.id.imgBack -> requireActivity().onBackPressed()
        }
    }
}