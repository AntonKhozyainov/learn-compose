package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class BusScheduleRepositoryImpl(
    private val busScheduleDao: BusScheduleDao
): BusScheduleRepository {
    override fun getBusSchedules(): Flow<List<BusSchedule>> =
        busScheduleDao.getBusSchedules()

    override fun getBusSchedulesByName(name: String): Flow<List<BusSchedule>> =
        busScheduleDao.getBusSchedulesByName(stopName = name)
}