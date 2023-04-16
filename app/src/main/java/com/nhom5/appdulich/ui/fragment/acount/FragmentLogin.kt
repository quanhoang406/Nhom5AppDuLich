package com.nhom5.appdulich.ui.fragment.acount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentLoginBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.utils.Validations
import com.nhom5.appdulich.viewmodel.account.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentLogin : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {
    @Inject
    lateinit var validation: Validations
    private val _viewModel by activityViewModels<LoginViewModel>()

    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        _viewModel.checkAccount {
            requireView().navigate(R.id.action_fragmentLogin_to_bottomNavigation)
        }
    }

    override fun listenerViewModel() {
        _viewModel.loadingDialog = {
            helpers.showProgressLoading(requireContext())
        }

        _viewModel.showError = {
            helpers.showAlertDialog(context = requireContext(), msg = it)
            helpers.dismissProgress()
        }
    }

    override fun onInit() {
        onClickView()
    }

    private fun onClickView() {
        binding.txtRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> _viewModel.login {
                requireView().navigate(R.id.action_fragmentLogin_to_bottomNavigation)
                helpers.dismissProgress()
            }
            R.id.txtRegister -> requireView().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }
    }
}