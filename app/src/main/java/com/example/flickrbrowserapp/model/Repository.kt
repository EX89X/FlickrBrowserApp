package com.example.flickrbrowserapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(var apiInterface: APIInterface) {

    var apiKey = "18d70c35da612c89b27be58b80f4df61"


    val mutableLivePhotoList= MutableLiveData<List<PhotoItem>>()

    fun getPhotos(searchText:String):LiveData<List<PhotoItem>>{
        apiInterface.getPhotos("flickr.photos.search", apiKey, searchText, "10",
            "json", "1").enqueue(object : Callback<Photos> {
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                var body = response.body()
                if (body != null) {
                    val photoList = arrayListOf<PhotoItem>()
                    val photosList = body.photos.photo
                    for (photo in photosList) {
                        val photoItem =
                            PhotoItem(photo.id, photo.server, photo.secret, photo.title)
                        photoList.add(photoItem)
                        Log.d("Tag", "onRespones:${photo.title}")
                    }
                    mutableLivePhotoList.postValue(photoList)
                }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Log.d("TAG", "onFailure:${t.message}")
            }
        })
      return mutableLivePhotoList
    }
}
