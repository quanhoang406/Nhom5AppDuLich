package com.nhom5.appdulich.ui.fragment.splash

import android.os.Handler
import android.os.Looper
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentSplashBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.utils.SharePrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSplash : BaseFragment<FragmentSplashBinding>() {
    @Inject
    lateinit var sharePrefs: SharePrefs

    override fun getViewBinding() = FragmentSplashBinding.inflate(layoutInflater)

    override fun listenerViewModel() {

    }

    override fun onInit() {
        Handler(Looper.myLooper()!!).postDelayed({
            requireView().navigate(R.id.action_fragmentSplash_to_fragmentLogin)
        }, 3000)
    }
}