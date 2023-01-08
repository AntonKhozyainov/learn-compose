package ru.khozyainov.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.khozyainov.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {

                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    ImageWithText(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun ImageWithText(modifier: Modifier = Modifier) {

    val stage = remember {
        mutableStateOf(Pair(1, 0))
    }

    val imageWithText = when (stage.value.first) {
        1 -> Pair(
            painterResource(id = R.drawable.lemon_tree),
            stringResource(id = R.string.lemon_tree)
        )
        2 -> Pair(
            painterResource(id = R.drawable.lemon_squeeze),
            stringResource(id = R.string.lemon)
        )
        3 -> Pair(
            painterResource(id = R.drawable.lemon_drink),
            stringResource(id = R.string.lemonade)
        )
        else -> Pair(
            painterResource(id = R.drawable.lemon_restart),
            stringResource(id = R.string.empty_glass)
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = imageWithText.second,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = imageWithText.first,
            contentDescription = null,
            modifier = modifier
                .clickable {
                    stage.value = setClick(stage.value)
                }
                .border(2.dp, Color(0xFF69CDD8), shape = RoundedCornerShape(4.dp))
        )


    }
}

private fun setClick(stage: Pair<Int, Int>): Pair<Int, Int> =
    when (stage.first) {
        1 -> Pair(2, 0)
        4 -> Pair(1, 0)
        else -> getPair(stage)
    }

private fun getPair(stage: Pair<Int, Int>): Pair<Int, Int> =
    if (stage.second >= (1..3).random()) Pair(stage.first + 1, 0)
    else Pair(stage.first, stage.second + 1)


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Greeting()
    }
}