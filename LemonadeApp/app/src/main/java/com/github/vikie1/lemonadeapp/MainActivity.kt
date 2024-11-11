package com.github.vikie1.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.vikie1.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun ImageWithLabel(modifier: Modifier = Modifier){
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeTapsNeeded by remember { mutableIntStateOf(1) }
    var currentSqueezeTaps by remember { mutableIntStateOf(1) }
    val label = when(currentStep){
        1 -> R.string.select_lemon
        2 -> R.string.squeeze_lemon
        3 -> R.string.drink_lemon
        else -> R.string.start_again
    }
    val image = when(currentStep){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val imageDescription = when(currentStep){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.lemonade
        else -> R.string.glass
    }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = imageDescription),
            modifier = Modifier
                .background(
                    color = Color(0xFFCCC2DC),
                    shape = RoundedCornerShape(20.dp),
                )
                .border(width = 2.dp, shape = RoundedCornerShape(20.dp), color = Color(red = 105, green = 205, blue = 216))
                .padding(2.dp)
                .clickable {
                when (currentStep) {
                    1 -> {
                        squeezeTapsNeeded = (1..5).random()
                        currentStep ++
                    }
                    2 -> {
                        if (currentSqueezeTaps == squeezeTapsNeeded) {
                            currentStep ++
                            currentSqueezeTaps = 0
                            squeezeTapsNeeded = 0
                        }
                        else currentSqueezeTaps ++
                    }
                    4 -> currentStep = 1
                    else -> currentStep ++
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = label), fontSize = 18.sp)
    }
}

@Preview
@Composable
fun LemonadeApp(){
    ImageWithLabel(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}