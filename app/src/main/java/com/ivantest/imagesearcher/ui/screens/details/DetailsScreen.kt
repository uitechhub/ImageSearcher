package com.ivantest.imagesearcher.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewStateWithHTMLData
import com.ivantest.imagesearcher.R
import com.ivantest.imagesearcher.model.FlickrImage

@Composable
fun FlickrDetailView(
    modifier: Modifier = Modifier,
    image: FlickrImage,
    onBack: () -> Unit
) {
    val webViewState = rememberWebViewStateWithHTMLData(data = image.description)

    Column(modifier = modifier.padding(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack, modifier = Modifier.size(44.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
            Text(text = image.title, style = MaterialTheme.typography.titleLarge)
        }
        Text(text = "Author: ${image.author}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Published: ${image.published}",
            style = MaterialTheme.typography.bodyMedium
        )
        WebView(state = webViewState)

    }
}