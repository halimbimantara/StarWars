package com.halim.starwars.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.halim.starwars.R
import com.halim.starwars.data.models.FilmResponse
import com.halim.starwars.databinding.ItemFilmLayoutBinding

class FilmsAdapter() :
    ListAdapter<FilmResponse, FilmsAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: ItemFilmLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: FilmResponse?, position: Int) {
            binding.textViewFilmName.text = character?.title
            val imageUrl = "https://starwars-visualguide.com/assets/img/films/${position}.jpg"
            Glide
                .with(binding.root.context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.sample_movie)
                .into(binding.imvCover);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemFilmLayoutBinding.inflate(
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
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<FilmResponse>() {
            override fun areItemsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: FilmResponse, newItem: FilmResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}