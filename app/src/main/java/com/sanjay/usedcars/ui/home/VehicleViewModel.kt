package com.sanjay.usedcars.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjay.usedcars.api.VehiclesRepository
import com.sanjay.usedcars.api.Resource
import com.sanjay.usedcars.model.VehiclesResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class VehicleViewModel : ViewModel() {
    var vehiclesLiveData: MutableLiveData<Resource<Any?>?> = MutableLiveData()

    fun getVehiclesAsync() {
        viewModelScope.launch {
            vehiclesLiveData.postValue(Resource.loading(null))
            try {
                coroutineScope {
                    val request = VehiclesRepository.getVehiclesAsync()
                    val response: Response<VehiclesResponse> = request.await()
                    if (response.isSuccessful) {
                        val vehiclesResponse: VehiclesResponse? = response.body()
                        vehiclesLiveData.postValue(
                            Resource.success(
                                data = vehiclesResponse
                            )
                        )
                    } else {
                        vehiclesLiveData.postValue(
                            Resource.error(
                                data = null,
                                message = response.message()
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                vehiclesLiveData.postValue(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}