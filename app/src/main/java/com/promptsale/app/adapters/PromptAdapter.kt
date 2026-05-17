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
            tvCategory.text = item.category
            tvTargetAI.text = item.targetAI.replace("Target AI:", "").trim()
            tvRating.text = item.rating.toString()
            tvDownloads.text = item.downloads
            ivPromptImage.setImageResource(item.imageRes)
            tvFree.text = if (item.isFree) "FREE" else "$9"
            btnGet.text = if (item.isFree) "GET" else "BUY"
            root.setOnClickListener { onClick(item) }
            btnGet.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size
}
