package com.promptsale.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.promptsale.app.databinding.ItemPromptListCardBinding
import com.promptsale.app.models.PromptItem

class CategoryPromptsAdapter(
    private var items: List<PromptItem>,
    private val onClick: (PromptItem) -> Unit
) : RecyclerView.Adapter<CategoryPromptsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPromptListCardBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPromptListCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvPromptTitle.text    = item.title
            tvCategoryTarget.text =
                "Category: ${item.category} | Target AI: ${item.targetAI}"
            tvRating.text    = item.rating.toString()
            tvDownloads.text = item.downloads
            ivPromptIcon.setImageResource(item.iconRes)

            root.setOnClickListener { onClick(item) }
            btnGet.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size

    fun updateList(newItems: List<PromptItem>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = items.size
            override fun getNewListSize() = newItems.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                items[oldPos].id == newItems[newPos].id
            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                items[oldPos] == newItems[newPos]
        })
        items = newItems
        diff.dispatchUpdatesTo(this)
    }
}
