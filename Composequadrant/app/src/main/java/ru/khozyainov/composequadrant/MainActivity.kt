package ru.khozyainov.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.khozyainov.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .weight(1f)
//            .fillMaxWidth()
//            .fillMaxHeight(0.5f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .background(Color.Green)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                TextTitle(value = R.string.firstQ)
                TextTitle2(value = R.string.firstQ2)

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                TextTitle(value = R.string.secondQ)
                TextTitle2(value = R.string.secondQ2)

            }

        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .background(Color.Cyan)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                TextTitle(value = R.string.thirdQ)
                TextTitle2(value = R.string.thirdQ2)

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                TextTitle(R.string.fourthQ)
                TextTitle2(R.string.fourthQ2)

            }

        }
    }
}

@Composable
fun TextTitle(@StringRes value: Int) {
    Text(
        text = stringResource(value),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .wrapContentWidth(Alignment.CenterHorizontally),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TextTitle2(@StringRes value: Int) {
    Text(
        text = stringResource(value),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        textAlign = TextAlign.Justify
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrant()
    }
}