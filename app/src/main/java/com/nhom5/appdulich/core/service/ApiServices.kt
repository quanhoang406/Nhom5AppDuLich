package com.nhom5.appdulich.core.service

import com.nhom5.appdulich.data.body.account.*
import com.nhom5.appdulich.data.response.*
import retrofit2.http.*

interface ApiServices {
    @POST("rest-api/User/login")
    suspend fun postLogin(
        @Body loginBody: LoginBody
    ): AccountResponse

    @POST("rest-api/User/register")
    suspend fun registerAccount(
        @Body registerBody: RegisterBody
    ): RegisterResponse

    @FormUrlEncoded
    @POST("rest-api/User/checkEmail")
    suspend fun checkEmail(
        @Field("email") email: String
    ): AccountResponse

    @PUT("rest-api/User/newPassword")
    suspend fun newPassword(
        @Body newPasswordBody: NewPasswordBody
    ): AccountResponse

    @POST("rest-api/User/updateUser")
    suspend fun updateProfile(
        @Body updateProfileBody: UpdateProfileBody
    ): AccountResponse

    @PUT("rest-api/User/updatePass")
    suspend fun changePassword(
        @Body changePasswordBody: ChangePasswordBody
    ): AccountResponse

    @GET("rest-api/Menu/getDataMenuAll")
    suspend fun getDataMenuAll(): MenuResponse

    @GET("rest-api/Place/getDataPlaceIdMenu")
    suspend fun getDataPlaceFromIdMenu(@Query("id") id: Int): PlaceReponse

    @GET("rest-api/Place/getDataPlaceStrSearch")
    suspend fun searchPlace(@Query("strSearch") strSearch: String?): PlaceReponse

    @GET("rest-api/Place/getPlaceFromName")
    suspend fun getPlaceFromName(@Query("name") name: String): PlaceReponse

    @GET("rest-api/Place/getDataBanerRandom")
    suspend fun getDataBannerRandom(): PlaceReponse

    @GET("rest-api/Menu/getDataMenuTop")
    suspend fun getDataMenuTop(): MenuResponse

    @GET("rest-api/Menu/getDataMenuBottom")
    suspend fun getDataMenuBottom(): MenuResponse

    @GET("rest-api/Event/getDataEventRanDom")
    suspend fun getDataEventRanDom(): EventResponse

    @GET("rest-api/Place/getDataPlaceHomeRandom")
    suspend fun getDataPlaceHomeRandom(
        @Query("id") id: Int,
        @Query("check") check: Int
    ): PlaceReponse

    @GET("rest-api/Place/getDataImageHomeRandom")
    suspend fun getDataImageHomeRandom(): PlaceReponse

    @GET("rest-api/Ingredient/getMenuIngredientFromIdMenu/id_menu={id}")
    suspend fun getMenuIngredientFromIdMenu(@Path("id") id : Int): IngredientMenuResponse

    @GET("rest-api/Place/getAllImagePlace")
    suspend fun getAllImagePlace(): PlaceReponse

    @GET("rest-api/Place/getDataPlaceIdIngredient")
    suspend fun getListPlaceIdIngredient(@Query("id") id: Int): PlaceReponse

    @GET("rest-api/Place/getDataPlaceIdPlace")
    suspend fun getDataPlaceIdPlace(@Query("id") id: Int): DetailResponse
}