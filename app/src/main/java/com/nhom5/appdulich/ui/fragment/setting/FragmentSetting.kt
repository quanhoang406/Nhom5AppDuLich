package com.nhom5.appdulich.ui.fragment.setting

import android.view.View
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSetting : BaseFragment<FragmentSettingBinding>(),View.OnClickListener{
    override fun getViewBinding() = FragmentSettingBinding.inflate(layoutInflater)

    override fun listenerViewModel() {

    }

    override fun onInit() {
        onClickView()
    }

    private fun onClickView() {
        binding.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgBack -> requireActivity().onBackPressed()
        }
    }
}