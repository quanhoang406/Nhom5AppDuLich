package com.nhom5.appdulich.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nhom5.appdulich.utils.Helpers
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    @Inject
    lateinit var helpers : Helpers

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        onInit()
        listenerViewModel()
    }

    protected abstract fun listenerViewModel()

    protected abstract fun onInit()

    protected abstract fun getViewBinding(): VB
}