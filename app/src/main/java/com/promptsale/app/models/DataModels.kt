package com.promptsale.app.models

data class FeaturedCollection(
    val id: Int,
    val title: String,
    val promptCount: String,
    val imageRes: Int,
    val bgColor: Int
)

data class Category(
    val id: Int,
    val name: String,
    val promptCount: String,
    val iconRes: Int,
    val bgColor: Int,
    val route: String
)

data class Prompt(
    val id: Int,
    val title: String,
    val category: String,
    val targetAI: String,
    val rating: Float,
    val downloads: String,
    val imageRes: Int,
    val isFree: Boolean,
    val description: String = "",
    val structureSections: List<String> = emptyList()
)

data class PromptItem(
    val id: Int,
    val title: String,
    val category: String,
    val targetAI: String,
    val rating: Float,
    val downloads: String,
    val iconRes: Int,
    val isFree: Boolean = true
)
data class FilterChip(
    val label: String,
    val color: Int,
    var isSelected: Boolean = false
)

sealed class CategoryType(
    val displayName: String,
    val searchHint: String,
    val filters: List<String>
) {
    object WebsiteBuilding : CategoryType(
        "Website Building Prompts",
        "Search Web Development Prompts...",
        listOf("All", "Frontend", "Backend", "Full Stack", "Frameworks", "CMS")
    )
    object FullAppDevelopment : CategoryType(
        "Full App Development Prompts",
        "Search App Development Prompts...",
        listOf("All", "Android", "iOS", "Flutter", "React Native", "Backend")
    )
    object CreativeWriting : CategoryType(
        "Creative Writing Prompts",
        "Search Creative Writing Prompts...",
        listOf("All", "Story", "Poetry", "Scripts", "Blog", "Copywriting")
    )
    object GraphicDesign : CategoryType(
        "Graphic Design Prompts",
        "Search Design Prompts...",
        listOf("All", "Logo", "UI/UX", "Branding", "Social Media", "Print")
    )
    object DataAnalysis : CategoryType(
        "Data Analysis Prompts",
        "Search Data Prompts...",
        listOf("All", "Python", "SQL", "Excel", "ML", "Visualization")
    )
    object MarketingStrategy : CategoryType(
        "Marketing Strategy Prompts",
        "Search Marketing Prompts...",
        listOf("All", "SEO", "Social", "Email", "Content", "Ads")
    )
}
