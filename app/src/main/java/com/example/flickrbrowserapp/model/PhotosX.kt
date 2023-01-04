package com.example.flickrbrowserapp.model

import com.example.flickrbrowserapp.model.Photo

data class PhotosX(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)