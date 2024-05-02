package com.github.vikie1.courseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.vikie1.courseapp.data.DataSource
import com.github.vikie1.courseapp.model.Topic
import com.github.vikie1.courseapp.ui.theme.CourseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CourseAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicsApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TopicContent(@StringRes topicName: Int, courseCount: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = topicName),
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_grain),
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = courseCount.toString(),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier){
    Card {
        Row(modifier = modifier) {
            Image(
                painter = painterResource(id = topic.topicImageResource),
                contentDescription = stringResource(id = topic.topicName),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(68.dp)
            )
            TopicContent(
                topicName = topic.topicName,
                courseCount = topic.courseCount,
                modifier = Modifier.padding(top = 16.dp, end = 16.dp)
            )
        }
    }
}

@Composable
fun TopicList(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(topics){topic -> 
            TopicCard(topic = topic, modifier = Modifier.height(68.dp))
        }
    }
}

@Preview
@Composable
fun TopicsApp(modifier: Modifier = Modifier){
    TopicList(topics = DataSource.topics, modifier = Modifier.padding(8.dp))
}