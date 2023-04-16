package com.nhom5.appdulich.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nhom5.appdulich.R
import com.nhom5.appdulich.base.response.DataResponse
import com.nhom5.appdulich.data.model.Menu
import com.nhom5.appdulich.data.model.Place
import com.nhom5.appdulich.repositories.MenuRepository
import com.nhom5.appdulich.repositories.PlaceRepository
import com.nhom5.appdulich.utils.Const
import com.nhom5.appdulich.utils.Validations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val _validation: Validations,
    private val _menuRepository: MenuRepository,
    private val _placeRepository: PlaceRepository,
    private val _application: Application
) : ViewModel() {

    var map: GoogleMap? = null
    var showError: ((String) -> Unit)? = null
    var loadingDialog: (() -> Unit)? = null

    fun isPermissionGrand(array: Array<String>) = _validation.isPermissionGrand(array)

    fun isGpsStatus() = _validation.checkGpsStatus()

    fun addMarker(place: Place) {
        map?.addMarker(
            MarkerOptions()
                .position(LatLng(place.lat!!.toDouble(), place.lng!!.toDouble()))
                .title(place.name)
        )
    }

    private fun addListMarker(places: List<Place>) {
        if (places.isNotEmpty()) {
            map?.clear()
            places.forEach {
                addMarker(it)
            }
            moveCamera(places[0])
            return
        }
        showError?.invoke(_application.getString(R.string.lbl_error_search))
    }

    fun moveCamera(place: Place) {
        map?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    place.lat!!.toDouble(),
                    place.lng!!.toDouble()
                ), Const.DEFAULT_ZOOM
            )
        )
    }

    fun getDataPlaceFromIdMenu(idMenu: Int, onSuccess: () -> Unit) = viewModelScope.launch {
        loadingDialog?.invoke()

        when (val value = _placeRepository.getDataPlaceFromIdMenu(idMenu)) {
            is DataResponse.Success -> {
                onSuccess()
                value.data.data?.let { addListMarker(it) }
            }
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }

    fun getMenuAll(onSuccess: (List<Menu>) -> Unit) = viewModelScope.launch {
        loadingDialog?.invoke()

        when (val value = _menuRepository.getAllMenu()) {
            is DataResponse.Success -> onSuccess(value.data.data!!)
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }

    fun searchPlace(str: String, onSuccess: () -> Unit) = viewModelScope.launch {
        loadingDialog?.invoke()

        when (val value = _placeRepository.searchPlace(str)) {
            is DataResponse.Success -> {
                onSuccess()
                value.data.data?.let { addListMarker(it) }
            }
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }

    fun getPlaceFromName(name: String, onSuccess: (Place) -> Unit) = viewModelScope.launch {
        if(name == _application.getString(R.string.lbl_my_location)){
            return@launch
        }
        loadingDialog?.invoke()

        when (val value = _placeRepository.getPlaceFromLng(name)) {
            is DataResponse.Success -> {
                if (value.data.data?.isNotEmpty() == true) {
                    onSuccess(value.data.data!![0])
                    return@launch
                }
                showError?.invoke(_application.getString(R.string.lbl_error_search))
            }
            is DataResponse.Fail -> showError?.invoke(value.exception.message.toString())
        }
    }

    fun findLocation(location: Location) {
        val place = Place(
            lat = location.latitude.toString(),
            lng = location.longitude.toString(),
            name = _application.getString(R.string.lbl_my_location)
        )
        addMarker(place)
        moveCamera(place)
    }
}