package com.promptsale.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.promptsale.app.databinding.ItemCategoryCardBinding
import com.promptsale.app.models.Category

class CategoryAdapter(
    private val items: List<Category>,
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCategoryCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvCategoryName.text = item.name
            tvCategoryCount.text = item.promptCount
            ivCategoryIcon.setImageResource(item.iconRes)
            categoryContainer.setBackgroundColor(
                ContextCompat.getColor(root.context, item.bgColor)
            )
            root.setOnClickListener { onClick(item) }
        }
    }

    override fun getItemCount() = items.size
}
