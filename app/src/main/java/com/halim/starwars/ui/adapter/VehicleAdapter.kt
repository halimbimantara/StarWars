package com.halim.starwars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.halim.starwars.data.models.VehicleResponse
import com.halim.starwars.databinding.ItemVehicleBinding

class VehicleAdapter() :
    ListAdapter<VehicleResponse, VehicleAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: VehicleResponse?, position: Int) {
            binding.tvName.text = character?.name
            binding.tvNameModel.text = "Model : " + character?.model
            binding.tvNameMan.text = "Manufacture : " + character?.manufacturer
//            val imageUrl = "https://starwars-visualguide.com/assets/img/films/${position}.jpg"
//            Glide
//                .with(binding.root.context)
//                .load(imageUrl)
//                .centerCrop()
//                .placeholder(R.drawable.sample_movie)
//                .into(binding.imvCover);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemVehicleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film, position)

    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<VehicleResponse>() {
            override fun areItemsTheSame(
                oldItem: VehicleResponse,
                newItem: VehicleResponse
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: VehicleResponse,
                newItem: VehicleResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}