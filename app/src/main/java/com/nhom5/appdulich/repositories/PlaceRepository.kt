package com.nhom5.appdulich.repositories

import com.nhom5.appdulich.base.BaseRepository
import com.nhom5.appdulich.core.room.FavoritePlace
import com.nhom5.appdulich.core.room.dao.PlaceDao
import com.nhom5.appdulich.core.service.ApiServices
import javax.inject.Inject

class PlaceRepository @Inject constructor(
    private val _api : ApiServices,
    private val _dao: PlaceDao
) : BaseRepository(){
    suspend fun insertPlace(place: FavoritePlace) {
        _dao.insert(place)
    }

    suspend fun getDataPlaceFromIdMenu(idMenu : Int) = callData {
        _api.getDataPlaceFromIdMenu(idMenu)
    }

    suspend fun searchPlace(str : String) = callData {
        _api.searchPlace(str)
    }

    suspend fun getPlaceFromLng(name : String) = callData {
        _api.getPlaceFromName(name)
    }

    suspend fun getDataBannerRandom() = callData {
        _api.getDataBannerRandom()
    }

    suspend fun getDataPlaceHomeRandom(idMenu : Int, check : Int) = callData {
        _api.getDataPlaceHomeRandom(idMenu,check)
    }

    suspend fun getDataImageHomeRandom() = callData {
        _api.getDataImageHomeRandom()
    }

    suspend fun getAllImagePlace() = callData {
        _api.getAllImagePlace()
    }

    suspend fun getListPlaceIdIngredient(id : Int) = callData {
        _api.getListPlaceIdIngredient(id)
    }

    suspend fun getDataPlaceIdPlace(id : Int) = callData {
        _api.getDataPlaceIdPlace(id)
    }

    //-------------------------MENU --------------------
    suspend fun getMenuTop() = callData {
        _api.getDataMenuTop()
    }

    suspend fun getDataMenuBottom() = callData {
        _api.getDataMenuBottom()
    }

    //-------------------------EVENT --------------------

    suspend fun getDataEventRanDom() = callData {
        _api.getDataEventRanDom()
    }

    //------------------------INGREDIENT MENU ---------
     suspend fun getMenuIngredientFromIdMenu(id : Int) = callData {
         _api.getMenuIngredientFromIdMenu(id)
    }
}