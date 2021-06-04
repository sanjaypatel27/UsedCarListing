package com.sanjay.usedcars.ui.home

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.sanjay.usedcars.databinding.VehicleCardLayoutBinding
import com.sanjay.usedcars.model.Listing

class VehicleViewHolder(
    private val binding: VehicleCardLayoutBinding,
    private val vehicleClickCallBack: VehicleClickCallBack
) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var listing: Listing

    init {
        binding.vehicleCard.setOnClickListener(this)
        binding.btnCallDealer.setOnClickListener(this)
    }

    fun bindData(listing: Listing) {
        this.listing = listing
        binding.vehicleYearMakeModelTrim.text = listing.getVehicleYearMakeModelTrimText()
        binding.vehiclePrice.text = listing.priceWithSymbol()

        val mileageText = "${listing.mileage}"
        binding.vehicleMileage.text = mileageText

        val vehicleLocationText = "${listing.dealer.city} , ${listing.dealer.state}"
        binding.vehicleLocation.text = vehicleLocationText

        if (TextUtils.isEmpty(listing.images.firstPhoto.large)) {
            binding.vehicleImage.visibility = View.GONE
        } else {
            binding.vehicleImage.visibility = View.VISIBLE
            binding.vehicleImage.load(listing.images.firstPhoto.large){
                scale(Scale.FIT)
            }
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.vehicleCard.id -> {
                vehicleClickCallBack.onVehicleCardClick(listing)
            }
            binding.btnCallDealer.id -> {
                vehicleClickCallBack.onVehicleCardCallDealerClick(listing)
            }
        }
    }

}