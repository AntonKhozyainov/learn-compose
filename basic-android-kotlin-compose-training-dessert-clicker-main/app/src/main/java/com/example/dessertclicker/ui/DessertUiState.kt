package com.example.dessertclicker.ui

import com.example.dessertclicker.model.Dessert

data class DessertUiState(
    val dessert: Dessert,
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
//    val currentDessertIndex: Int = 0,
//    val currentDessertPrice: Int = 0,
//    val currentDessertImageId: Int = 0,
)
