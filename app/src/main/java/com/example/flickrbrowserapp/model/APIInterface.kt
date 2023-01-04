package com.example.flickrbrowserapp.model

import com.example.flickrbrowserapp.model.Photos
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("rest/")
    fun getPhotos(@Query("method")method:String,
                  @Query("api_key")api_key:String,
                  @Query("tags")tags:String,
                  @Query("per_page")per_page:String,
                  @Query("format")format:String,
                  @Query("nojsoncallback")nojsoncallback:String): retrofit2.Call<Photos>
}
