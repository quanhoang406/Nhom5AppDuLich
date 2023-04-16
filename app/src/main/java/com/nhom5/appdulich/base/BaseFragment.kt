package com.nhom5.appdulich.base

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.nhom5.appdulich.utils.Helpers
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected val permissions by lazy { arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION) }

    @Inject
    lateinit var helpers: Helpers

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
        listenerViewModel()
    }

    protected abstract fun listenerViewModel()

    protected abstract fun onInit()
}