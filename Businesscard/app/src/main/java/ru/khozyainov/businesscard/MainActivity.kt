package ru.khozyainov.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.khozyainov.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D4C56))
    ) {
        LogoWithName(Modifier.weight(1f))
        Contacts(Modifier.weight(0.7f))
    }
}

@Composable
fun LogoWithName(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight(align = Alignment.Bottom)
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = stringResource(R.string.content_description_android),
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .width(90.dp)
        )
        Text(
            text = stringResource(R.string.my_name),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight(300)
        )
        Text(
            text = stringResource(R.string.major),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            color = Color(0xFF1AB255)
        )

    }


}


@Composable
fun Contacts(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight(align = Alignment.Bottom)
            .fillMaxWidth()
            .padding(bottom = 30.dp)
    ) {

        Divider()
        Contact(image = R.drawable.ic_baseline_local_phone_24, contactValue = R.string.phone)
        Divider()
        Contact(image = R.drawable.ic_baseline_share_24, contactValue = R.string.cont)
        Divider()
        Contact(image = R.drawable.ic_baseline_email_24, contactValue = R.string.mail)

    }
}

@Composable
fun Divider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color.White)
    ) {}
}

@Composable
fun Contact(
    @DrawableRes image: Int,
    @StringRes contactValue: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 82.dp, bottom = 6.dp, top = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(image),
            contentDescription = null,
            tint = Color(0xFF1AB255),
            modifier = Modifier
                .size(30.dp),
        )
        Text(
            text = stringResource(contactValue),
            modifier = Modifier
                .padding(start = 22.dp),
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight(350)
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        Greeting()
    }
}