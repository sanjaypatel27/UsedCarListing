package com.sanjay.usedcars.ui.home

import com.sanjay.usedcars.model.Listing


interface VehicleClickCallBack {
    fun onVehicleCardClick(listing: Listing)
    fun onVehicleCardCallDealerClick(listing: Listing)
}