package com.ivantest.imagesearcher.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivantest.imagesearcher.api.data.SearchRepository
import com.ivantest.imagesearcher.model.FlickrImage
import com.ivantest.imagesearcher.model.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> get() = _uiState

    fun onQueryChanged(newQuery: String) {
        _uiState.update { searchUiState -> searchUiState.copy(query = newQuery) }
        if (newQuery.isNotEmpty()) {
            performSearch(newQuery)
        } else {
            _uiState.update { searchUiState -> searchUiState.copy(images = listOf()) }
        }
    }

    fun updateSelectedImage(selectedImage: FlickrImage?) {
        _uiState.update { state -> state.copy(selectedImage = selectedImage) }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _uiState.update { searchUiState -> searchUiState.copy(isLoading = true) }
            val result = searchRepository.searchImages(query)
            _uiState.update { searchUiState -> searchUiState.copy(isLoading = false, images = result) }
        }
    }

}