package com.ivantest.imagesearcher.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ivantest.imagesearcher.ui.screens.search.FlickrSearchScreen
import com.ivantest.imagesearcher.ui.screens.search.SearchViewModel
import com.ivantest.imagesearcher.ui.theme.ImageSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageSearcherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FlickrSearchScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = searchViewModel
                    )
                }
            }
        }
    }
}