package com.promptsale.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.promptsale.app.databinding.ItemFavoriteBinding
import com.promptsale.app.models.Prompt

class FavoriteAdapter(
    private val items: List<Prompt>,
    private val onClick: (Prompt) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvFavoriteTitle.text = item.title
            tvFavoriteCategory.text = item.category
            tvFavoriteRating.text = item.rating.toString()
            ivFavoriteImage.setImageResource(item.imageRes)
            btnView.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size
}
