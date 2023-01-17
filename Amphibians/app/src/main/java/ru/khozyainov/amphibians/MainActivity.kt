package ru.khozyainov.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.khozyainov.amphibians.ui.screens.AmphibiansApp
import ru.khozyainov.amphibians.ui.screens.AmphibiansViewModel
import ru.khozyainov.amphibians.ui.theme.AmphibiansTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmphibiansTheme {
                val viewModel: AmphibiansViewModel by viewModels()
                AmphibiansApp(viewModel)
            }
        }
    }
}


