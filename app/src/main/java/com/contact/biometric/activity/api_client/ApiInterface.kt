package com.contact.biometric.activity.api_client

import com.contact.biometric.activity.model.ApiResponseModel
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("api/")
    fun searching(@Query("key") key: String?, @Query("q") q: String?): Call<ApiResponseModel?>?
}