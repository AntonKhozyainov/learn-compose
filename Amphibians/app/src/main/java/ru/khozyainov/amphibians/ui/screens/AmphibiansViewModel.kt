package ru.khozyainov.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.khozyainov.amphibians.data.Amphibian
import ru.khozyainov.amphibians.data.AmphibianRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AmphibiansViewModel @Inject constructor(
    private val repository: AmphibianRepository
): ViewModel() {

    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibian()
    }

    fun getAmphibian() =
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(repository.getAmphibians())
            }catch (e: IOException){
                AmphibianUiState.Error
            }
        }

    sealed interface AmphibianUiState {
        data class Success(val amphibians: List<Amphibian>) : AmphibianUiState
        object Error : AmphibianUiState
        object Loading : AmphibianUiState
    }
}