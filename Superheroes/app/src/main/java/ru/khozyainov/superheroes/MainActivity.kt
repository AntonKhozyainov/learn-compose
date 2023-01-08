package ru.khozyainov.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.khozyainov.superheroes.data.Hero
import ru.khozyainov.superheroes.data.heroes
import ru.khozyainov.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                HeroesApp()
            }
        }
    }
}

@Composable
fun HeroesApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            HeroesTopAppBar()
        }
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(heroes) { hero ->
                HeroCard(hero = hero)
            }
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            HeroNameAndDescription(hero = hero)
            HeroImage(hero = hero)
        }

    }
}

@Composable
fun HeroImage(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = hero.imageRes),
        contentDescription = "heroImage",
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun HeroNameAndDescription(
    hero: Hero,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(end = 16.dp)
            .fillMaxWidth(0.8f)
    ) {
        Text(
            text = stringResource(id = hero.nameRes),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(id = hero.descriptionRes),
            style = MaterialTheme.typography.body1,
            maxLines = 2
        )
    }
}

@Composable
fun HeroesTopAppBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme(darkTheme = false) {
        HeroesApp()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewDarkTheme() {
    SuperheroesTheme(darkTheme = true) {
        HeroesApp()
    }
}