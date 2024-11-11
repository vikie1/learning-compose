package com.github.vikie1.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.vikie1.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageComposable()
                }
            }
        }
    }
}
@Composable
fun IntroSection(fullName: String, title: String, modifier: Modifier = Modifier){
    val icon = painterResource(id = R.drawable.android_logo)
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
        )
        Text(
            text = fullName,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color(0xD187FFFF)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = Color(0xD187FFFF)
        )
    }
}
@Composable
fun ContactSection(phone: String, socialMediaHandle: String, email: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        Row (modifier = Modifier.padding(top = 8.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.call_black_24),
                contentDescription = null,
                tint = Color(0xFFCCC2DC)
            )
            Text(
                text = phone,
                color = Color(0xFFEFB8C8)
            )
        }
        Row (modifier = Modifier.padding(top = 8.dp)){
            Icon(
                painter = painterResource(id = R.drawable.baseline_social_media_24),
                contentDescription = null,
                tint = Color(0xFFCCC2DC)
            )
            Text(
                text = socialMediaHandle,
                color = Color(0xFFEFB8C8)
            )
        }
        Row (modifier = Modifier.padding(top = 8.dp)){
            Icon(
                painter = painterResource(id = R.drawable.baseline_email_24),
                contentDescription = null,
                tint = Color(0xFFCCC2DC)
            )
            Text(
                text = email,
                color = Color(0xFFEFB8C8)
            )
        }
    }
}
@Composable
fun PageComposable(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = Color(0xFF625b71))
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(weight = 1F))
        IntroSection(
            fullName = "Victor Mwangi",
            title = "Mobile Software Developer(Android)",
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(weight = 1F))
        ContactSection(
            phone = "+254 748 340 130",
            socialMediaHandle = "@victormwangi_",
            email = "mwangivictor52@gmail.com",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        PageComposable()
    }
}