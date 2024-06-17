package com.github.vikie1.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.vikie1.amphibians.R
import com.github.vikie1.amphibians.network.Amphibian
import com.github.vikie1.amphibians.ui.model.AmphibiansUiState
import com.github.vikie1.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(amphibiansUiState: AmphibiansUiState, retryAction: () -> Unit, modifier: Modifier = Modifier, paddingValues: PaddingValues = PaddingValues(0.dp)){
    when(amphibiansUiState){
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
        is AmphibiansUiState.Success -> AmphibianFeed(
            amphibians = amphibiansUiState.amphibians,
            modifier = modifier
        )
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier){
    Card(modifier, elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.small))) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.small))) {
            Text(
                text = amphibian.name,
                style = MaterialTheme.typography.titleLarge
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .build(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.baseline_broken_image_24),
                placeholder = painterResource(id = R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.medium))
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun AmphibianFeed(amphibians: List<Amphibian>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)){
    LazyColumn(modifier = modifier, contentPadding = contentPadding) {
        items(items = amphibians){ amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.small))
            )
        }
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(id = R.string.loading_failed)
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.medium))
        )
        Button(onClick = retryAction,) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    AmphibiansTheme {
        AmphibianCard(amphibian = Amphibian(
            name = "An amphibian",
            type = "Toad",
            description = "lorem ipsum set do",
            imgSrc = ""
        ))
    }
}