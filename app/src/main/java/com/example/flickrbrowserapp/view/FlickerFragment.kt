package com.example.flickrbrowserapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.model.APIInterface
import com.example.flickrbrowserapp.model.APIclient
import com.example.flickrbrowserapp.R
import com.example.flickrbrowserapp.databinding.FragmentFlickerBinding
import com.example.flickrbrowserapp.model.PhotoItem
import com.example.flickrbrowserapp.model.Photos
import com.example.flickrbrowserapp.viewmodel.FlickerViewModel
import kotlinx.android.synthetic.main.fragment_flicker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlickerFragment : Fragment(), RecyclerViewAdapter.PhotoClickInterface {

               var rvShown = true
               val photoList = arrayListOf<PhotoItem>()
               lateinit var flickerViewModel:FlickerViewModel
               lateinit var binding: FragmentFlickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFlickerBinding.inflate(layoutInflater)
        flickerViewModel=ViewModelProvider(this).get(FlickerViewModel::class.java)
        val adapter = RecyclerViewAdapter(this)
        binding.rvMain.adapter = adapter

        binding.ImageView.setOnClickListener {


            showRv()
            rvShown = true

        }
            binding.searchBTN.setOnClickListener {
                val searchText = binding.SearchEt.text.toString()
                flickerViewModel.getPhotos(searchText).observe(viewLifecycleOwner){photoList->
                    adapter.submitList(photoList)
                }

                }

           return binding.root
            }
            fun showRv() {
                binding.apply {
                    ImageView.visibility = View.GONE
                    rv_Main.visibility = View.VISIBLE

                }
            }

            fun hideRv(link: String) {
                binding.apply {
                    Glide.with(requireActivity()).load(link).into(ImageView)
                    ImageView.visibility = View.VISIBLE
                    rv_Main.visibility = View.GONE

                }
            }

            override fun onPhotoClick(photoItem: PhotoItem) {
                val serverID = photoItem.server
                val secret = photoItem.secret
                val id = photoItem.id

                var link = "https://live.staticflickr.com/${serverID}/${id}_${secret}.jpg"
                if (rvShown) {
                    hideRv(link)
                    rvShown = false
                } else {
                    showRv()
                }
            }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
            }
          }