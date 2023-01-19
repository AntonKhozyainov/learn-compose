package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {

    fun getBusSchedules(): Flow<List<BusSchedule>>

    fun getBusSchedulesByName(name: String): Flow<List<BusSchedule>>
}