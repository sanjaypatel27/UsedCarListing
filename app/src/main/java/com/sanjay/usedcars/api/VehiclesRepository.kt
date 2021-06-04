package com.sanjay.usedcars.api


object VehiclesRepository {
    fun getVehiclesAsync() = ApiFactory.vehicleService.getVehiclesAsync()
}