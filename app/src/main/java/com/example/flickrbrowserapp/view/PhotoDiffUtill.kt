package com.example.flickrbrowserapp.view

import androidx.recyclerview.widget.DiffUtil
import com.example.flickrbrowserapp.model.PhotoItem

class PhotoDiffUtill():DiffUtil.ItemCallback<PhotoItem>(){
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem==newItem
    }
}