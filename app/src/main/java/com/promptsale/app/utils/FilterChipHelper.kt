package com.promptsale.app.utils

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.promptsale.app.R

object FilterChipHelper {

    fun buildChips(
        context: Context,
        container: LinearLayout,
        filters: List<String>,
        selectedFilter: String,
        onFilterSelected: (String) -> Unit
    ) {
        container.removeAllViews()

        filters.forEach { filter ->
            val chip = TextView(context).apply {
                text    = filter
                gravity = Gravity.CENTER
                setPadding(
                    dpToPx(context, 18), dpToPx(context, 6),
                    dpToPx(context, 18), dpToPx(context, 6)
                )
                textSize = 13f

                val isSelected = filter == selectedFilter

                if (isSelected) {
                    setBackgroundResource(R.drawable.chip_selected_bg)
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    elevation = dpToPx(context, 2).toFloat()
                } else {
                    setBackgroundResource(R.drawable.chip_unselected_bg)
                    setTextColor(ContextCompat.getColor(context, R.color.text_secondary))
                    elevation = 0f
                }

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, dpToPx(context, 8), 0) }
                layoutParams = params

                setOnClickListener {
                    buildChips(context, container, filters, filter, onFilterSelected)
                    onFilterSelected(filter)
                }
            }
            container.addView(chip)
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int =
        (dp * context.resources.displayMetrics.density).toInt()
}
