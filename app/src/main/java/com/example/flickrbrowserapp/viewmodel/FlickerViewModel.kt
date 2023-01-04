package com.example.flickrbrowserapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flickrbrowserapp.model.APIInterface
import com.example.flickrbrowserapp.model.APIclient
import com.example.flickrbrowserapp.model.PhotoItem
import com.example.flickrbrowserapp.model.Repository

class FlickerViewModel():ViewModel() {

     lateinit var repository: Repository

              init{
                  val apiClient=APIclient().getClient()
                    if (apiClient!=null){
                        val apiInterface = apiClient.create(APIInterface::class.java)
                        repository= Repository(apiInterface)
                  }
              }
           fun getPhotos(searchText:String):LiveData<List<PhotoItem>>{
               return repository.getPhotos(searchText)
           }
       }