package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState(dessert = Datasource.dessertList.first()))
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun buyTheDessert(){
        _uiState.update { dessertUiState ->
            dessertUiState.copy(
                dessert = determineDessertToShow(),
                dessertsSold = dessertUiState.dessertsSold.inc(),
                revenue = dessertUiState.revenue + dessertUiState.dessert.price
            )
        }
    }

    private fun determineDessertToShow(): Dessert {
        var dessertToShow = Datasource.dessertList.first()
        for (dessert in Datasource.dessertList) {
            if (_uiState.value.dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}