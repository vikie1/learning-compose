package com.github.vikie1.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.vikie1.superheroes.model.Hero
import com.github.vikie1.superheroes.model.HeroesRepository

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(16.dp)){
        Row(modifier = modifier) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.size(72.dp)) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = stringResource(id = hero.nameRes),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = MaterialTheme.shapes.small)
                )
            }
        }
    }
}

@Composable
fun HeroesList(heroes: List<Hero>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(heroes) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            ) {
                HeroItem(
                    hero = it,
                    modifier = Modifier
                        .height(72.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun HeroesListPreview(){
    val heroes = HeroesRepository.heroes
    HeroesList(
        heroes = heroes
    )
}