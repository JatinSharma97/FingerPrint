package com.contact.biometric.activity.api_client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 open class ApiClient {


     companion object {
         var retrofit: Retrofit? = null

         // live on this url
         val BASEURL = "https://pixabay.com/"
         fun getClient(): Retrofit? {
             val gson: Gson = GsonBuilder()
                 .setLenient()
                 .create()
             if (retrofit == null) {
                 retrofit = Retrofit.Builder().baseUrl(BASEURL)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .build()
             }
             return retrofit
         }
     }
}