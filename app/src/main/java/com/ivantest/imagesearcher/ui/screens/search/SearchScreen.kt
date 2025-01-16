package com.ivantest.imagesearcher.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ivantest.imagesearcher.ui.components.SearchBar
import com.ivantest.imagesearcher.ui.screens.details.FlickrDetailView

@Composable
fun FlickrSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.selectedImage == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth(),
                query = uiState.query,
                onQueryChanged = viewModel::onQueryChanged,
                onSearch = viewModel::onQueryChanged
            )

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(30.dp))
            }

            if (uiState.images.isNotEmpty()) {
                Spacer(modifier = Modifier.padding(top = 12.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 2.dp
                ) {
                    println("showing vertical grid for items size ${uiState.images.size}")
                    items(uiState.images.size) { index ->
                        val image = uiState.images[index]
                        val height = if (index % 3 == 0) 160.dp else 80.dp
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height)
                                    .clickable { viewModel.updateSelectedImage(image) },
                                painter = rememberAsyncImagePainter(
                                    model = image.imageUrl,
                                    contentScale = ContentScale.Fit
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit
                            )
                    }
                }
            }
        }
    } else {
        FlickrDetailView(
            modifier = modifier,
            image = uiState.selectedImage!!
        ) { viewModel.updateSelectedImage(null) }
    }
}