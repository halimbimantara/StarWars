package com.halim.starwars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.halim.starwars.data.models.SpeciesResponse
import com.halim.starwars.databinding.ItemVehicleBinding

class SpeciesAdapter() :
    ListAdapter<SpeciesResponse, SpeciesAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: SpeciesResponse?, position: Int) {
            binding.tvName.text = character?.name
            binding.tvNameModel.text = "Classification : " + character?.classification
            binding.tvNameMan.text = "Designation : " + character?.designation
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
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<SpeciesResponse>() {
            override fun areItemsTheSame(
                oldItem: SpeciesResponse,
                newItem: SpeciesResponse
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: SpeciesResponse,
                newItem: SpeciesResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}