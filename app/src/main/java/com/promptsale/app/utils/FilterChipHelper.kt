package com.promptsale.app.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.promptsale.app.R

object FilterChipHelper {

    private val chipColors = listOf(
        "#1565C0", // Blue  - All
        "#2E7D32", // Green - filter 2
        "#C62828", // Red   - filter 3
        "#6A1B9A", // Purple- filter 4
        "#E65100", // Orange- filter 5
        "#F57F17"  // Yellow- filter 6
    )

    fun buildChips(
        context: Context,
        container: LinearLayout,
        filters: List<String>,
        selectedFilter: String,
        onFilterSelected: (String) -> Unit
    ) {
        container.removeAllViews()

        filters.forEachIndexed { index, filter ->
            val chip = TextView(context).apply {
                text    = filter
                gravity = Gravity.CENTER
                setPadding(
                    dpToPx(context, 18), dpToPx(context, 6),
                    dpToPx(context, 18), dpToPx(context, 6)
                )
                textSize = 13f

                val color = chipColors.getOrElse(index) { "#1565C0" }
                val isSelected = filter == selectedFilter

                if (isSelected) {
                    setBackgroundResource(R.drawable.chip_selected_bg)
                    background.setTint(Color.parseColor(color))
                    setTextColor(Color.WHITE)
                } else {
                    setBackgroundResource(R.drawable.chip_unselected_bg)
                    setTextColor(Color.parseColor(color))
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
