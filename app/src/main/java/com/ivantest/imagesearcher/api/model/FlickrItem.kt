package com.ivantest.imagesearcher.api.model

data class FlickrItem(
    val title: String,
    val media: Media,
    val description: String,
    val author: String,
    val published: String
)