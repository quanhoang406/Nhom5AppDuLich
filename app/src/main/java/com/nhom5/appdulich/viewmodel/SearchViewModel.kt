package com.nhom5.appdulich.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhom5.appdulich.base.response.DataResponse
import com.nhom5.appdulich.data.model.Place
import com.nhom5.appdulich.repositories.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val _placeRepository: PlaceRepository): ViewModel() {
    var showError: ((String) -> Unit)? = null
    private val _searchPlaces = MutableLiveData<List<Place>>()
    val searchPlaces: LiveData<List<Place>>
        get() = _searchPlaces

    fun searchPlace(str: String, onSuccess: () -> Unit) = viewModelScope.launch {
        when (val value = _placeRepository.searchPlace(str)) {
            is DataResponse.Success -> {
                onSuccess()
                value.data.data?.let { _searchPlaces.value = it }
            }
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }
}