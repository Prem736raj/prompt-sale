package com.promptsale.app.ui.category

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.promptsale.app.R
import com.promptsale.app.adapters.CategoryPromptsAdapter
import com.promptsale.app.databinding.FragmentCategoryPromptsBinding
import com.promptsale.app.models.CategoryType
import com.promptsale.app.models.PromptItem
import com.promptsale.app.utils.FilterChipHelper

class CategoryPromptsFragment : Fragment() {

    private var _binding: FragmentCategoryPromptsBinding? = null
    private val binding get() = _binding!!

    private val args: CategoryPromptsFragmentArgs by navArgs()
    private lateinit var adapter: CategoryPromptsAdapter
    private var allPrompts = listOf<PromptItem>()
    private var currentFilter = "All"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryPromptsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryRoute = args.categoryRoute
        val categoryType = getCategoryType(categoryRoute)

        setupUI(categoryType)
        setupRecyclerView(categoryType)
        setupSearch()
        setupBackButton()
    }

    private fun getCategoryType(route: String): CategoryType {
        return when (route) {
            "website_building"     -> CategoryType.WebsiteBuilding
            "full_app_development" -> CategoryType.FullAppDevelopment
            "creative_writing"     -> CategoryType.CreativeWriting
            "graphic_design"       -> CategoryType.GraphicDesign
            "data_analysis"        -> CategoryType.DataAnalysis
            "marketing_strategy"   -> CategoryType.MarketingStrategy
            else                   -> CategoryType.WebsiteBuilding
        }
    }

    private fun setupUI(categoryType: CategoryType) {
        binding.tvCategoryTitle.text = categoryType.displayName
        binding.etSearch.hint = categoryType.searchHint

        // Build filter chips
        FilterChipHelper.buildChips(
            context         = requireContext(),
            container       = binding.filterChipsContainer,
            filters         = categoryType.filters,
            selectedFilter  = currentFilter
        ) { selected ->
            currentFilter = selected
            filterPrompts(binding.etSearch.text.toString())
        }
    }

    private fun setupRecyclerView(categoryType: CategoryType) {
        allPrompts = getPromptsForCategory(categoryType)

        adapter = CategoryPromptsAdapter(allPrompts) { prompt ->
            Toast.makeText(
                requireContext(),
                "Opening: ${prompt.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvPrompts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPrompts.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterPrompts(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterPrompts(query: String) {
        val filtered = allPrompts.filter { prompt ->
            val matchesSearch = query.isEmpty() ||
                prompt.title.contains(query, ignoreCase = true) ||
                prompt.category.contains(query, ignoreCase = true) ||
                prompt.targetAI.contains(query, ignoreCase = true)

            val matchesFilter = currentFilter == "All" ||
                prompt.category.contains(currentFilter, ignoreCase = true)

            matchesSearch && matchesFilter
        }
        adapter.updateList(filtered)
    }

    private fun setupBackButton() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getPromptsForCategory(categoryType: CategoryType): List<PromptItem> {
        return when (categoryType) {
            is CategoryType.WebsiteBuilding    -> getWebsiteBuildingPrompts()
            is CategoryType.FullAppDevelopment -> getFullAppPrompts()
            is CategoryType.CreativeWriting    -> getCreativeWritingPrompts()
            is CategoryType.GraphicDesign      -> getGraphicDesignPrompts()
            is CategoryType.DataAnalysis       -> getDataAnalysisPrompts()
            is CategoryType.MarketingStrategy  -> getMarketingPrompts()
        }
    }

    private fun getWebsiteBuildingPrompts(): List<PromptItem> = listOf(
        PromptItem(101, "Full-Stack E-Commerce Website", "Full Stack", "ChatGPT-4", 4.9f, "12k", R.drawable.ic_web_ecommerce),
        PromptItem(102, "Responsive Portfolio Website", "Frontend", "Claude 3", 4.9f, "8k", R.drawable.ic_web_portfolio),
        PromptItem(103, "React Admin Dashboard", "Frameworks", "Gemini", 4.8f, "6k", R.drawable.ic_web_react),
        PromptItem(104, "RESTful API with Authentication", "Backend", "ChatGPT-4", 4.7f, "9k", R.drawable.ic_web_api),
        PromptItem(105, "WordPress Theme from Scratch", "CMS", "ChatGPT-4", 4.6f, "5k", R.drawable.ic_web_wordpress),
        PromptItem(106, "Next.js Landing Page", "Full Stack", "Claude 3", 4.8f, "7k", R.drawable.ic_web_nextjs)
    )

    private fun getFullAppPrompts(): List<PromptItem> = listOf(
        PromptItem(201, "Complete Social Media App", "Android", "ChatGPT-4", 4.9f, "15k", R.drawable.ic_app_social),
        PromptItem(202, "Food Delivery App with Maps", "Flutter", "Gemini", 4.8f, "11k", R.drawable.ic_app_food),
        PromptItem(203, "Fitness Tracker with AI Coach", "Android", "ChatGPT-4", 4.8f, "8k", R.drawable.ic_app_fitness)
    )

    private fun getCreativeWritingPrompts(): List<PromptItem> = listOf(
        PromptItem(301, "Epic Fantasy Novel Outline", "Story", "ChatGPT-4", 4.9f, "20k", R.drawable.ic_write_fantasy),
        PromptItem(302, "Viral Blog Post Formula", "Blog", "Claude 3", 4.8f, "18k", R.drawable.ic_write_blog)
    )

    private fun getGraphicDesignPrompts(): List<PromptItem> = listOf(
        PromptItem(401, "Complete Brand Identity System", "Branding", "Midjourney", 4.9f, "22k", R.drawable.ic_design_brand)
    )

    private fun getDataAnalysisPrompts(): List<PromptItem> = listOf(
        PromptItem(501, "Python Data Analysis Pipeline", "Python", "ChatGPT-4", 4.9f, "19k", R.drawable.ic_data_python)
    )

    private fun getMarketingPrompts(): List<PromptItem> = listOf(
        PromptItem(601, "Complete Go-To-Market Strategy", "Strategy", "ChatGPT-4", 4.9f, "28k", R.drawable.ic_mkt_seo)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
