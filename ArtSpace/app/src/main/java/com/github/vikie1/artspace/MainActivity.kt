package com.github.vikie1.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.vikie1.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp))
                }
            }
        }
    }
}
@Composable
fun ArtImage(@DrawableRes image: Int, modifier: Modifier = Modifier){
    Surface (
        modifier = modifier.background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp)),
        shadowElevation = 15.dp
    ) {
        Image(
            painter = painterResource(image), // act as place holder
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .border(color = Color.White, width = 24.dp)
        )
    }
}
@Composable
fun ArtDescription(@StringRes title: Int, @StringRes artist: Int, year: Int, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            .padding(8.dp),
    ) {
        Text(text = stringResource(id = title), fontSize = 24.sp)
        Row {
            Text(text = stringResource(id = artist), fontWeight = FontWeight.Bold)
            Text(text = stringResource(id = R.string.year, year), modifier = Modifier.padding(horizontal = 4.dp))
        }
    }
}
@Composable
fun NavigationButtons(onPreviousClicked: () -> Unit, onNextClicked: () -> Unit, modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPreviousClicked) {
            Text(text = stringResource(R.string.previous))
        }
        Button(onClick = onNextClicked) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier){
    var position by remember { mutableIntStateOf(1) }
    val image = updateImageArt(position)
    val title = updateArtTitle(position)
    val year = updateArtYear(position)

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1F))
        ArtImage(image)
        Spacer(Modifier.weight(1F))
        ArtDescription(
            title = title,
            artist = R.string.artist,
            year = year
        )
        Spacer(Modifier.height(16.dp))
        NavigationButtons(
            onPreviousClicked = {if (position > 1) position -- else position = 7},
            onNextClicked = {if (position < 7) position ++ else position = 1}
        )
    }
}

private fun updateImageArt(position: Int) : Int{
    return when(position){
        1 -> R.drawable.amarantha
        2 -> R.drawable.clothline
        3 -> R.drawable.fence
        4 -> R.drawable.flower
        5 -> R.drawable.flowers
        6 -> R.drawable.grasshopper
        else -> R.drawable.grasshopper_pro
    }
}
private fun updateArtTitle(position: Int) : Int{
    return when(position){
        1 -> R.string.amaranth
        2 -> R.string.clothesline
        3 -> R.string.fence
        4 -> R.string.flower
        5 -> R.string.flowers
        6 -> R.string.grasshopper
        else -> R.string.grasshopper_pro
    }
}
private fun updateArtYear(position: Int) : Int{
    return when(position){
        1 -> 2024
        2 -> 2023
        3 -> 2021
        4 -> 2024
        5 -> 2022
        6 -> 2024
        else -> 2023
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
    }
}