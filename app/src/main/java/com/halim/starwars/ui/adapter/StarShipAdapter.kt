package com.halim.starwars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.halim.starwars.data.models.StarShipResponse
import com.halim.starwars.databinding.ItemStarshipBinding

class StarShipAdapter() :
    ListAdapter<StarShipResponse, StarShipAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: ItemStarshipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: StarShipResponse?, position: Int) {
            binding.tvName.text = character?.name
            binding.tvNameModel.text = "Model : "+character?.model
            binding.tvNameMan.text = "Manufacture : "+character?.manufacturer
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
            ItemStarshipBinding.inflate(
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
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<StarShipResponse>() {
            override fun areItemsTheSame(oldItem: StarShipResponse, newItem: StarShipResponse): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: StarShipResponse, newItem: StarShipResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}