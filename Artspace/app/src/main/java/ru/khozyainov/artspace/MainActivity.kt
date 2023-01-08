package ru.khozyainov.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.khozyainov.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtWithDescriptionAndButtons()
            }
        }
    }
}

@Composable
fun ArtWithDescriptionAndButtons(modifier: Modifier = Modifier) {

    val artCount = remember { mutableStateOf(1) }

    val imageWithDescription = getTripleRes(artCount.value)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        //.background(color = Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Art(
            image = imageWithDescription.first,
            modifier = modifier
        )

        DescriptionAtr(
            title = imageWithDescription.third,
            author = imageWithDescription.second,
            modifier = modifier
        )

        ButtonsPrevNext(
            value = artCount.value,
            modifier = modifier,
            onValueChange = {
                artCount.value = it
            }
        )
    }
}

@Composable
fun ButtonsPrevNext(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {
                onValueChange(getCurrentCount(value = value, isPlus = false))
            },
            modifier = modifier.weight(40f)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }

        Spacer(modifier = modifier.padding(40.dp))

        Button(
            onClick = {
                onValueChange(getCurrentCount(value = value, isPlus = true))
            },
            modifier = modifier.weight(40f)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

private fun getCurrentCount(value: Int, isPlus: Boolean): Int {
    val result = if (isPlus) value + 1 else value - 1
    return when {
        result in 1..3 -> result
        result <= 0 -> 3
        else -> 1
    }
}

@Composable
fun DescriptionAtr(
    @StringRes title: Int,
    @StringRes author: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = 24.dp
    ) {
        Column(
            modifier = modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = title),
                modifier = modifier,
                fontSize = 24.sp
            )
            Text(
                //text = stringResource(id = author),
                modifier = modifier,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(
                            stringResource(id = author)
                        )
                    }
                    append(" ello")
                }

            )
        }
    }
}

@Composable
fun Art(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        elevation = 24.dp,
        border = BorderStroke(4.dp, Color.Gray)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.image),
            modifier = modifier
                .fillMaxHeight(0.6F)
                .padding(30.dp),
            contentScale = ContentScale.Crop
        )
    }

}

private fun getTripleRes(count: Int): Triple<Int, Int, Int> =
    when (count) {
        1 -> Triple(
            R.drawable.dice_3,
            R.string.dice_author,
            R.string.dice_title
        )
        2 -> Triple(
            R.drawable.android_logo,
            R.string.logo_author,
            R.string.logo_title
        )
        else -> Triple(
            R.drawable.lemon_squeeze,
            R.string.lemon_author,
            R.string.lemon_title
        )
    }

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtWithDescriptionAndButtons()
    }
}