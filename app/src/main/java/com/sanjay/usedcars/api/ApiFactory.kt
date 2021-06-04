package com.sanjay.usedcars.api

object ApiFactory {
    val vehicleService: VehicleInterface = Service.retrofit.create(VehicleInterface::class.java)
}