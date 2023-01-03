package com.example.flickrbrowserapp

import androidx.recyclerview.widget.DiffUtil

class PhotoDiffUtill():DiffUtil.ItemCallback<PhotoItem>(){
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem==newItem
    }
}