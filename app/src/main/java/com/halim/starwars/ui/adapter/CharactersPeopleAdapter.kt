package com.halim.starwars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.halim.starwars.data.models.PeopleCharResponse
import com.halim.starwars.databinding.ItemPeopleLayoutBinding

class CharactersPeopleAdapter(private val onClickListener: OnClickListener) :
    PagingDataAdapter<PeopleCharResponse, CharactersPeopleAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    inner class MyViewHolder(private val binding: ItemPeopleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: PeopleCharResponse?) {
            binding.tvNameCharacter.text = character?.name
            binding.tvByCharacter.text = "Birth year :"+character?.birthYear
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPeopleLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(character!!)
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<PeopleCharResponse>() {
            override fun areItemsTheSame(oldItem: PeopleCharResponse, newItem: PeopleCharResponse): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: PeopleCharResponse, newItem: PeopleCharResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (character: PeopleCharResponse) -> Unit) {
        fun onClick(character: PeopleCharResponse) = clickListener(character)
    }
}