package com.nhom5.appdulich.ui.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nhom5.appdulich.R
import com.nhom5.appdulich.databinding.FragmentSearchBinding
import com.nhom5.appdulich.extension.navigate
import com.nhom5.appdulich.ui.adapter.search.SearchAdapter
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.utils.Helpers
import com.nhom5.appdulich.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentSearch : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchAdapter()

    @Inject
    lateinit var helpers: Helpers

    override fun onResume() {
        super.onResume()
        getDataSearch("")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        eventSearch()
        loadDataFailure()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = {place ->
            requireView().navigate(R.id.action_global_fragmentDetailPlace,Bundle().apply {
                putInt(Const.KEY_ID,place.id)
            })
        }
    }

    private fun getDataSearch(namePlace: String) {
        binding.rvPlaceSearch.adapter = adapter
        viewModel.searchPlace(namePlace) {
            viewModel.searchPlaces.observe(viewLifecycleOwner, {
                adapter.submitList(it)
            })
        }
    }

    private fun loadDataFailure() {
        viewModel.showError = {
            helpers.showToast(it)
        }
    }

    private fun eventSearch() {
        binding.edSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.edSearch.text.toString().let {
                    getDataSearch(it)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
