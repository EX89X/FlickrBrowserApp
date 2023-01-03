package com.example.flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


import com.example.flickrbrowserapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.item_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.PhotoClickInterface {

    //  val API_KEY = "a1dd998eaa7204d1ea6f0680a36698af"
    var apiKey = "18d70c35da612c89b27be58b80f4df61"
  //  val link = "https://www.flickr.com/services/rest/?method=flickr.photos.search$apiKey=2972eb32d5cf4c187dc742ad23541f52&tags=cat&per_page=10&format=json&nojsoncallback=1"
    // val photoLink="https://live.staticflicker.com/$serverID/${id}_${secret}.jpg"
     var rvShown=true

    lateinit var binding: ActivityMainBinding
    val photoList = arrayListOf<PhotoItem>()
    lateinit var myrc1: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myrc1 = findViewById(R.id.rv_Main)
        val adapter = RecyclerViewAdapter(this)
        binding.rvMain.adapter = adapter

        binding.ImageView.setOnClickListener {
              showRv()
              rvShown=true

        }

        binding.searchBTN.setOnClickListener {
            val searchText = binding.SearchEt.text.toString()
            val apiClient = APIclient().getClient()
            if (apiClient != null) {
                val apiInterface = apiClient.create(APIInterface::class.java)
                apiInterface.getPhotos(
                    "flickr.photos.search",
                    apiKey,
                    searchText,
                    "10",
                    "json",
                    "1"
                ).enqueue(object : Callback<Photos> {
                    override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                        var body = response.body()
                        if (body != null) {
                            val photosList = body.photos.photo
                            for (photo in photosList) {
                                val photoItem =
                                    PhotoItem(photo.id, photo.server, photo.secret, photo.title)
                                photoList.add(photoItem)
                                Log.d("Tag", "onRespones:${photo.title}")
                            }
                            adapter.submitList(photoList)
                        }
                    }

                    override fun onFailure(call: Call<Photos>, t: Throwable) {
                        Log.d("TAG", "onFailure:${t.message}")
                    }
                })

            }
        }
    }

    fun showRv() {
        binding.apply {
            ImageView.visibility = View.GONE
            myrc1.visibility = View.VISIBLE

        }
    }

        fun hideRv(link:String) {
            binding.apply {
                Glide.with(this@MainActivity).load(link).into(ImageView)
                ImageView.visibility = View.VISIBLE
                myrc1.visibility = View.GONE

            }
        }
    override fun onPhotoClick(photoItem: PhotoItem) {
        val serverID =photoItem.server
        val secret = photoItem.secret
        val id = photoItem.id

        var  link="https://live.staticflickr.com/${serverID}/${id}_${secret}.jpg"
        if(rvShown){
            hideRv(link)
            rvShown=false
        }else{
            showRv()
        }
    }

}

