package net.nshiba.eatsenju.networks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val endpoint = "https://script.googleusercontent.com/"

    fun default() : Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(endpoint)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
}
