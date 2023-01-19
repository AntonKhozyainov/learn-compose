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

package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleRepository
import kotlinx.coroutines.flow.*

class BusScheduleViewModel(
    private val busScheduleRepository: BusScheduleRepository
): ViewModel() {

//    val busScheduleUiState: StateFlow<BusScheduleUiiState> =
//        getFullSchedule().map { BusScheduleUiiState(list = it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                initialValue = BusScheduleUiiState()
//            )

    // Get example bus schedule from Room DB
    fun getFullSchedule(): Flow<List<BusSchedule>> = busScheduleRepository.getBusSchedules()
    // Get example bus schedule by stop
    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busScheduleRepository.getBusSchedulesByName(name = stopName)

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BusScheduleApplication)
                BusScheduleViewModel(
                    application.container.busScheduleRepository
                )
            }
        }
    }
}

//data class BusScheduleUiiState(
//    val list: List<BusSchedule> = listOf(),
//    val id: Int = 0,
//    val stopName: String = "",
//    val arrivalTimeInMillis: Int = 0
//)
