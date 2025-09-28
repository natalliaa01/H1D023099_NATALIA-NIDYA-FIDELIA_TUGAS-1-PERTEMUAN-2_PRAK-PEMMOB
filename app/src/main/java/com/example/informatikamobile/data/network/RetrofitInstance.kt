package com.example.informatikamobile.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.informatikamobile.utils.Constants

object RetrofitInstance {
    val api: OpenLibraryApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // tanpa factory =
            .build()
            .create(OpenLibraryApi::class.java)
    }
}


