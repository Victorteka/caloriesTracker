package com.victorteka.caloriestracker.ui.newMeal.recently

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.victorteka.caloriestracker.R
import com.victorteka.caloriestracker.databinding.RecentSearchedFoodBinding
import com.victorteka.domain.models.Common


class RecentSearchedFoodAdapter(private val foods: ArrayList<Common>) :
    RecyclerView.Adapter<RecentSearchedFoodAdapter.RecentViewHolder>() {

    var onFoodClick: ((Common) -> Unit)? = null

    inner class RecentViewHolder(private val binding: RecentSearchedFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onFoodClick?.invoke(foods[adapterPosition])
            }
        }

        fun bind(food: Common) {
            binding.foodNameTV.text = food.food_name
            Glide.with(binding.foodImage.context)
                .load(food.photo.thumb)
                .into(binding.foodImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            RecentSearchedFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(foods[position])
    }

    override fun getItemCount() = foods.size

    fun addData(list: List<Common>) {
        foods.clear()
        foods.addAll(list)
    }
}