package com.assignment.dogbreeds.application.presentation.fragments.breedList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import com.assignment.dogbreeds.databinding.LayoutItemBreedListBinding
import com.bumptech.glide.Glide
import javax.inject.Inject

class DogBreedRecyclerViewAdapter @Inject constructor() :
    ListAdapter<Breed, DogBreedRecyclerViewAdapter.BreedViewHolder>(DiffCallback()) {
    var listener: ((Breed) -> Unit)? = null
    fun setOnBreedClickListener(plistener: (Breed) -> Unit) {
        listener = plistener
    }

    inner class BreedViewHolder(private val binding: LayoutItemBreedListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(breed: Breed) {
            binding.txtName.text = breed.name
            binding.txtNoOfSubbreeds.text = "No of Breeds: ${breed.noOfSubBreeds}"
            Glide.with(binding.root.context).load(breed.image).into(binding.imgBreed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return BreedViewHolder(LayoutItemBreedListBinding.inflate(inflator, parent, false))
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = getItem(position)
        holder.bindView(breed)
        holder.itemView.setOnClickListener { listener?.invoke(breed) }

    }

    class DiffCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
            return oldItem == newItem
        }
    }
}