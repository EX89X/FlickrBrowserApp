package com.example.flickrbrowserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.item_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val API_KEY="2972eb32d5cf4c187dc742ad23541f52"
  // val photoLink="https://live.staticflicker.com/$serverID/${id}_${secret}.jpg"
  //  val link="https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=2972eb32d5cf4c187dc742ad23541f52&tags=cat&per_page=10&format=json&nojsoncallback=1"

    lateinit var binding:ActivityMainBinding
    val photoList= arrayListOf<PhotoItem>()
    lateinit var myrc1:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myrc1=findViewById(R.id.rvMain)
        val adapter=RecyclerViewAdapter(this,photoList)
        binding.rvMain.adapter=adapter

        binding.ImageView.setOnClickListener {
            showRv()

        }


        binding.searchBTN.setOnClickListener {
            val searchText=binding.SearchEt.text.toString()
            val apiClient=APIClient().getClient()
            if(apiClient!=null){
                val apiInterface=apiClient.create(APIInterface::class.java)
                apiInterface.getPhotos("flickr.photos.search",API_KEY,searchText,10,"json",1).enqueue(object:Callback<Photos> {
                    override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                        var body=response.body()
                        if(body != null){
                            val photosList =body.photos.photo
                            for(photo in photosList){
                                val PhotoItem=PhotoItem(photo.id,photo.server,photo.secret)
                                photoList.add(PhotoItem)
                               // Log.d("Tag","onRespones:${photo.title}")
                            }
                            adapter.updatePhotosList(photoList)
                        }
                    }

                    override fun onFailure(call: Call<Photos>, t: Throwable) {
                        Log.d("TAG","onFailure:${t.message}")
                    }
                })

            }
        }
    }
    fun showRv(){
        binding.apply {
            ImageView1.visibility= View.GONE
            myrc1.visibility=View.VISIBLE

        }
    }

    fun hideRv(link:String){
        Glide.with(this@MainActivity).load(link).into(ImageView1)
        ImageView1.visibility= View.VISIBLE
        myrc1.visibility=View.GONE


    }
}