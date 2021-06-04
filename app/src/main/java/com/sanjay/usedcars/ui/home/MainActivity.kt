package com.sanjay.usedcars.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanjay.usedcars.api.Service
import com.sanjay.usedcars.api.Status
import com.sanjay.usedcars.databinding.ActivityMainBinding
import com.sanjay.usedcars.manager.VehicleDetailScreenManager
import com.sanjay.usedcars.manager.VehiclesManger
import com.sanjay.usedcars.model.Listing
import com.sanjay.usedcars.model.VehiclesResponse


class MainActivity : AppCompatActivity(), VehicleClickCallBack {
    private lateinit var binding: ActivityMainBinding
    private val vehicleViewModel: VehicleViewModel by viewModels()
    private val vehicleAdaptor: VehicleAdaptor = VehicleAdaptor(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vehiclesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.vehiclesRecyclerView.adapter = vehicleAdaptor
        vehicleAdaptor.listings = VehiclesManger.listings

        viewModelObserver()
        getData()

    }

    private fun getData() {
        if (VehiclesManger.listings.isEmpty()) {
            vehicleViewModel.getVehiclesAsync()
        }
    }

    private fun viewModelObserver() {
        vehicleViewModel.vehiclesLiveData.observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (it.data is VehiclesResponse) {
                            val vehiclesResponse: VehiclesResponse = it.data
                            Log.d(Service.TAG, "Success : $vehiclesResponse")
                            VehiclesManger.listings = vehiclesResponse.listings
                            vehicleAdaptor.listings = VehiclesManger.listings

                        }
                    }
                    Status.ERROR -> {
                        Log.d(Service.TAG, "ERROR : ${resource.message}")
                    }
                    Status.LOADING -> {
                        Log.d(Service.TAG, "LOADING")
                    }
                }
            }
        })

    }

    override fun onVehicleCardClick(listing: Listing) {
        VehicleDetailScreenManager.selectedListing = listing
    }

    override fun onVehicleCardCallDealerClick(listing: Listing) {
        val intent = Intent(Intent.ACTION_DIAL)
        val number = "tel:${listing.dealer.phone}"
        intent.data = Uri.parse(number)
        startActivity(intent)
    }
}