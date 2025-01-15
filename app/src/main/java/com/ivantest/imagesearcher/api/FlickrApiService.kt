package com.ivantest.imagesearcher.api

import com.ivantest.imagesearcher.api.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchImages(@Query("tags") tags: String): FlickrResponse

    companion object {
        const val BASE_URL = "https://api.flickr.com/"
    }
}