package com.sanjay.usedcars.model

import java.text.NumberFormat
import java.util.*

typealias Listings = List<Listing>


data class VehiclesResponse(
    val listings: Listings
)


data class Listing(
    val id: String,
    val model: String,
    val vin: String,
    val make: String,
    val year: String,
    val trim: String,
    val drivetype: String,
    val bodytype: String,
    val transmission: String,
    val interiorColor: String,
    val exteriorColor: String,
    val currentPrice: Double,
    val mileage: Double,
    val images: Images,
    val dealer: Dealer
) {
    fun priceWithSymbol(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.maximumFractionDigits = 2
        format.minimumFractionDigits = 0
        return format.format(currentPrice)
    }

    fun getVehicleYearMakeModelTrimText(): String {
        return "$year $make $model $trim"
    }
}

data class Dealer(
    val name: String,
    val address: String,
    val phone: String,
    val state: String,
    val city: String,
)

data class Images(
    val baseUrl: String,
    val firstPhoto: FirstPhoto
)

data class FirstPhoto(
    val large: String
)

