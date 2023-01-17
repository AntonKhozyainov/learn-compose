package ru.khozyainov.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.khozyainov.amphibians.R
import ru.khozyainov.amphibians.data.Amphibian

@Composable
fun AmphibiansApp(
    viewModel: AmphibiansViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ){
            MainScreen(
                amphibianUiState = viewModel.amphibianUiState,
                modifier = modifier
            )
        }
    }

}

@Composable
fun MainScreen(
    amphibianUiState: AmphibiansViewModel.AmphibianUiState,
    modifier: Modifier = Modifier
){
    when (amphibianUiState) {
        is AmphibiansViewModel.AmphibianUiState.Loading -> LoadingScreen(modifier)
        is AmphibiansViewModel.AmphibianUiState.Success -> AmphibiansList(amphibianUiState.amphibians)
        is AmphibiansViewModel.AmphibianUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun AmphibiansList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ){
        items(amphibians, key = {amphibian -> amphibian.name}){ amphibian ->
            AmphibianCard(
                amphibian = amphibian,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                fontSize = 16.sp,
                modifier = modifier
                    .padding(top = 20.dp, start = 6.dp, end = 6.dp),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = amphibian.description,
                fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Justify
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    //crossfade(true) чтобы включить плавную анимацию при успешном выполнении запроса
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.amphibian_photo),
                //заполнить доступное пространство на экране
                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = modifier.width(380.dp).height(250.dp)
            )
        }

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AmphibiansAppPreview() {
    val list: MutableList<Amphibian> = mutableListOf()
    repeat(10){
        list.add(
            Amphibian(
                name = "$it Great Basin Spadefoot",
                type = "Toad",
                description = "This toad spends most of its life underground due to the arid desert " +
                        "conditions in which it lives. Spadefoot toads earn the name because of their " +
                        "hind legs which are wedged to aid in digging. They are typically grey, green, " +
                        "or brown with dark spots.",
                imgSrc = ""
            )
        )
    }
    AmphibiansList(
        list
    )
}