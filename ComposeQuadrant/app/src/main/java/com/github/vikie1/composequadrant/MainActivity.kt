package com.github.vikie1.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.vikie1.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadrantsComposable(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun TextComposable(title: String, body: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body, textAlign = TextAlign.Justify)
    }
}
@Composable
fun ImageComposable(title: String, body: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body, textAlign = TextAlign.Justify)
    }
}
@Composable
fun RowComposable(title: String, body: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body, textAlign = TextAlign.Justify)
    }
}
@Composable
fun ColumnComposable(title: String, body: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = body, textAlign = TextAlign.Justify)
    }
}
@Composable
fun QuadrantsComposable(modifier: Modifier = Modifier){
    Column (modifier) {
        Row (modifier = Modifier.fillMaxWidth().weight(weight = 1F)) {
            TextComposable(
                title = "Text composable",
                body = "Displays text and follows the recommended Material Design guidelines.",
                modifier = Modifier
                    .background(color = Color(0xFFEADDFF))
                    .weight(weight = 1F)
                    .fillMaxHeight()
            )
            ImageComposable(
                title = "Image composable",
                body = "Creates a composable that lays out and draws a given Painter class object.",
                modifier = Modifier
                    .background(color = Color(0xFFD0BCFF))
                    .weight(weight = 1F)
                    .fillMaxHeight()
            )
        }
        Row (modifier = Modifier.fillMaxWidth().weight(weight = 1F)) {
            RowComposable(
                title = "Row composable",
                body = "A layout composable that places its children in a horizontal sequence.",
                modifier = Modifier
                    .background(color = Color(0xFFB69DF8))
                    .weight(weight = 1F)
                    .fillMaxHeight()
            )
            ColumnComposable(
                title = "Column composable",
                body = "A layout composable that places its children in a vertical sequence.",
                modifier = Modifier
                    .background(color = Color(0xFFF6EDFF))
                    .weight(weight = 1F)
                    .fillMaxHeight()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        QuadrantsComposable(modifier = Modifier.fillMaxSize())
    }
}