package com.nhom5.appdulich.ui.fragment.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentAccountBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.ui.page.MainActivity
import com.nhom5.appdulich.viewmodel.SettingAccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAccount : BaseFragment<FragmentAccountBinding>() {
    private val _viewModel by activityViewModels<SettingAccountViewModel>()

    override fun getViewBinding() = FragmentAccountBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        _viewModel.getAccount()
    }

    override fun listenerViewModel() {
        _viewModel.showError = {
            helpers.showAlertDialog(msg = it, context = requireContext())
            requireView().navigate(R.id.action_fragmentAcount_to_fragmentLogin)
        }
    }

    override fun onInit() {
        onClickView()
    }

    private fun onClickView() {
        binding.rowAccountProfile.relativeGroup.setOnClickListener {
            requireView().navigate(R.id.action_fragmentAcount_to_fragmentMyProfile)
        }

        binding.rowAccountSupport.relativeGroup.setOnClickListener {

        }

        binding.rowAccountAbout.relativeGroup.setOnClickListener {
            requireView().navigate(R.id.action_fragmentAcount_to_fragmentAbout)
        }

        binding.rowAccountSetting.relativeGroup.setOnClickListener {
            requireView().navigate(R.id.action_fragmentAcount_to_fragmentSetting)
        }

        binding.rowAccountLogout.relativeGroup.setOnClickListener {
            _viewModel.logout {
                MainActivity.navController.navigate(R.id.action_bottomNavigation_to_fragmentLogin)
            }
        }
    }
}