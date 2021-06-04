package com.sanjay.usedcars.api

import kotlinx.coroutines.Deferred
import com.sanjay.usedcars.model.VehiclesResponse
import retrofit2.Response
import retrofit2.http.GET

interface VehicleInterface {
    @GET("assignment.json")
    fun getVehiclesAsync(): Deferred<Response<VehiclesResponse>>

}