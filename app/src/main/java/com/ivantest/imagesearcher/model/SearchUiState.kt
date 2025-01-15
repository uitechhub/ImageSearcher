package com.ivantest.imagesearcher.model

data class SearchUiState(
    val query: String = "",
    val images: List<FlickrImage> = emptyList(),
    val isLoading: Boolean = false,
    val selectedImage: FlickrImage? = null
)