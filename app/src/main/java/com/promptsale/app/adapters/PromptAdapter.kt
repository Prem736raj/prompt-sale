package com.promptsale.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.promptsale.app.databinding.ItemPromptCardBinding
import com.promptsale.app.models.Prompt

class PromptAdapter(
    private val items: List<Prompt>,
    private val onClick: (Prompt) -> Unit
) : RecyclerView.Adapter<PromptAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPromptCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPromptCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvPromptTitle.text = item.title
            tvCategory.text = "Category"
            tvTargetAI.text = item.targetAI
            tvRating.text = item.rating.toString()
            tvDownloads.text = item.downloads
            ivPromptImage.setImageResource(item.imageRes)
            if (item.isFree) {
                tvFree.text = "FREE"
                btnGet.text = "GET"
            }
            root.setOnClickListener { onClick(item) }
            btnGet.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size
}
