package com.promptsale.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.promptsale.app.databinding.ItemFeaturedCardBinding
import com.promptsale.app.models.FeaturedCollection

class FeaturedAdapter(
    private val items: List<FeaturedCollection>,
    private val onClick: (FeaturedCollection) -> Unit
) : RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFeaturedCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeaturedCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvFeaturedTitle.text = item.title
            tvFeaturedCount.text = item.promptCount
            ivFeaturedBg.setImageResource(item.imageRes)
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size
}
