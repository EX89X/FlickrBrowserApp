package com.example.flickrbrowserapp.model
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIclient{
    var retrofit:Retrofit? = null
    fun getClient():Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit

    }
}