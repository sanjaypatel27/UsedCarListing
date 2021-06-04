package com.sanjay.usedcars.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.usedcars.databinding.VehicleCardLayoutBinding
import com.sanjay.usedcars.extensions.autoNotify
import com.sanjay.usedcars.model.Listing
import com.sanjay.usedcars.model.Listings
import kotlin.properties.Delegates

class VehicleAdaptor(private val vehicleClickCallBack: VehicleClickCallBack) :
    RecyclerView.Adapter<VehicleViewHolder>() {
    var listings: List<Listing> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VehicleViewHolder(
        VehicleCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false), vehicleClickCallBack)

    override fun getItemCount(): Int {
        return listings.size
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bindData(listings[position])
    }
}