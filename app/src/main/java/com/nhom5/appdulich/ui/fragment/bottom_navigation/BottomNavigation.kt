package com.nhom5.appdulich.ui.fragment.bottom_navigation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.BaseFragment
import com.nhom5.appdulich.databinding.FragmentBottomNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavigation : BaseFragment<FragmentBottomNavigationBinding>() {
    private lateinit var _navHostFragment: NavHostFragment

    companion object {
        lateinit var navController: NavController
    }

    override fun getViewBinding() = FragmentBottomNavigationBinding.inflate(layoutInflater)

    override fun listenerViewModel() {

    }

    override fun onInit() {
        createNavController()
        initView()
        onClickView()
    }

    private fun onClickView() {
        binding.fab.setOnClickListener {
            navController.navigate(R.id.action_global_fragmentMap)
        }
    }

    private fun createNavController() {
        _navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = _navHostFragment.navController
    }

    private fun initView() {
        binding.bottomNavigate.apply {
            setupWithNavController(navController)
            background = null
        }
    }
}