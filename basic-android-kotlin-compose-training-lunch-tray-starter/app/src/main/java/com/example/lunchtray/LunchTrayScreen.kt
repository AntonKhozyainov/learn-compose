/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.*

enum class LunchTrayScreen(
    @StringRes val title: Int
) {
    START(title = R.string.app_name),
    ENTREE_MENU(title = R.string.choose_entree),
    SIDE_DISH_MENU(title = R.string.choose_side_dish),
    ACCOMPANIMENT_MENU(title = R.string.choose_accompaniment),
    CHECKOUT(title = R.string.order_checkout),

}

@Composable
fun LunchTrayAppBar(
    currentScreen: LunchTrayScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    orderViewModel: OrderViewModel = viewModel()
) {

    //Создаем NavHostController, который обрабатывает добавление ComposeNavigator и DialogNavigator
    val navController = rememberNavController()

    //Когда данный navController изменит стек это вызовет рекомпозицию и вернет верхнюю запись в стеке
    val backStackEntry by navController.currentBackStackEntryAsState()

    //Получаем текущий элемент стека
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.START.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        val uiState by orderViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.START.name,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(route = LunchTrayScreen.START.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.ENTREE_MENU.name)
                    }
                )
            }

            composable(route = LunchTrayScreen.ENTREE_MENU.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SIDE_DISH_MENU.name)
                    },
                    onSelectionChanged = { entreeItem ->
                        orderViewModel.updateEntree(entreeItem)
                    }
                )
            }

            composable(route = LunchTrayScreen.SIDE_DISH_MENU.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.ACCOMPANIMENT_MENU.name)
                    },
                    onSelectionChanged = { sideDishItem ->
                        orderViewModel.updateSideDish(sideDishItem)
                    }
                )
            }

            composable(route = LunchTrayScreen.ACCOMPANIMENT_MENU.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.CHECKOUT.name)
                    },
                    onSelectionChanged = { accompanimentItem ->
                        orderViewModel.updateAccompaniment(accompanimentItem)
                    }
                )
            }

            composable(route = LunchTrayScreen.CHECKOUT.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(orderViewModel, navController)
                    })
            }
        }
    }
}


private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.START.name, inclusive = false)
}
