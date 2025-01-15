package com.ivantest.imagesearcher.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ivantest.imagesearcher.model.FlickrImage

@Composable
fun FlickrDetailView(
    image: FlickrImage,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = onBack) { Text("Back") }
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = rememberAsyncImagePainter(image.imageUrl), contentDescription = null)
        Text(text = image.title, style = MaterialTheme.typography.titleLarge)
        Text(text = image.description, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Author: ${image.author}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Published: ${image.published}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}