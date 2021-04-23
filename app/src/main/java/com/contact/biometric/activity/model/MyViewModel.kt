package com.contact.biometric.activity.model

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.contact.biometric.R
import com.contact.biometric.activity.MyProgress
import com.contact.biometric.activity.api_client.ApiClient
import com.contact.biometric.activity.api_client.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    var apiInterface: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
    private var responsePojo: MutableLiveData<ApiResponseModel?>? = null
    var apiKey: String ="21281621-fb0f025a0fc2e6e5a257254d4"

    //check internet connection off or on
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }


//hit api
    fun getUserChannelModelLiveData(
        activity: Activity?,
        searchText: String?
    ): LiveData<ApiResponseModel?> {
        responsePojo = MutableLiveData()
            if (activity != null) {
                MyProgress.show(activity)
            }

        if(if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                isOnline(activity!!.applicationContext)
            } else {

                TODO("VERSION.SDK_INT < LOLLIPOP")
            }
        ) {

            apiInterface.searching(apiKey, searchText)
                ?.enqueue(object : Callback<ApiResponseModel?> {
                    override fun onResponse(
                        call: Call<ApiResponseModel?>,
                        response: Response<ApiResponseModel?>
                    ) {
                        MyProgress.cancel()
                        if (response.body() != null) {

                            responsePojo!!.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ApiResponseModel?>, t: Throwable) {
                        MyProgress.cancel()
                        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })

        }else{
            MyProgress.cancel()
            Toast.makeText(activity,"Please Check your internet Connection",Toast.LENGTH_SHORT).show()
        }
        return responsePojo!!
    }
}