package com.ivantest.imagesearcher.api.data

import com.ivantest.imagesearcher.api.FlickrApiService
import com.ivantest.imagesearcher.model.FlickrImage
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val flickrApiService: FlickrApiService
): SearchRepository {

    override suspend fun searchImages(tags: String): List<FlickrImage> {
        return runCatching {
            flickrApiService.searchImages(tags).items.map { item ->
                FlickrImage(
                    title = item.title,
                    imageUrl = item.media.m,
                    description = item.description,
                    author = item.author,
                    published = formatDate(item.published)
                )
            }
        }.getOrDefault(listOf())
    }

    private fun formatDate(dateString: String): String {
        return runCatching {
            val inputFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault())
            val outputFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date!!)
        }.getOrDefault("N/A")
    }

    companion object {
        const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val OUTPUT_DATE_FORMAT = "MMM dd, yyyy HH:mm"
    }
}