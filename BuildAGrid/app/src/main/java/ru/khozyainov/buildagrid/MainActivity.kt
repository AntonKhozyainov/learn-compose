package ru.khozyainov.buildagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.khozyainov.buildagrid.ui.theme.BuildAGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildAGridTheme {
                TopicsGrid()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicsGrid(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(DataSource.topics){ topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(
    topic: Topic,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = stringResource(id = topic.title),
                modifier = modifier.size(60.dp)
            )
            Column {
                Text(
                    text = stringResource(id = topic.title),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.count.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

//            Column(
//                modifier = modifier.padding(top = 16.dp, start = 16.dp)
//            ) {
//                Text(
//                    text = stringResource(id = topic.title),
//                    fontSize = 12.sp
//                )
//
//                Spacer(modifier = modifier.padding(2.dp))
//
//                Row {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_grain),
//                        contentDescription = null,
//                        modifier = modifier.size(16.dp)
//                    )
//                    Spacer(modifier = modifier.padding(8.dp))
//                    Text(
//                        text = topic.count.toString(),
//                        fontSize = 12.sp
//                    )
//                }
//
//            }
        }


    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    BuildAGridTheme {
        TopicsGrid()
    }
}