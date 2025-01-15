package com.ivantest.imagesearcher.api.data

import com.ivantest.imagesearcher.model.FlickrImage

interface SearchRepository {
    suspend fun searchImages(tags: String): List<FlickrImage>
}