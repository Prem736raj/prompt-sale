package com.promptsale.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.promptsale.app.R
import com.promptsale.app.adapters.CategoryAdapter
import com.promptsale.app.adapters.FeaturedAdapter
import com.promptsale.app.adapters.PromptAdapter
import com.promptsale.app.databinding.FragmentHomeBinding
import com.promptsale.app.models.Category
import com.promptsale.app.models.FeaturedCollection
import com.promptsale.app.models.Prompt

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val handler = android.os.Handler(android.os.Looper.getMainLooper())
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFeaturedViewPager()
        setupCategoryRecyclerView()
        setupPopularPrompts()
    }

    private fun setupFeaturedViewPager() {
        val featuredList = listOf(
            FeaturedCollection(1, "Create Full Apps from AI", "150+ Prompts",
                R.drawable.featured_bg_1, R.color.featured_dark),
            FeaturedCollection(2, "Master Content Writing", "200+ Prompts",
                R.drawable.featured_bg_2, R.color.featured_blue),
            FeaturedCollection(3, "Generate Stunning AI Art", "300+ Prompts",
                R.drawable.featured_bg_3, R.color.featured_purple)
        )

        val adapter = FeaturedAdapter(featuredList) { featured ->
            // Handle click
        }
        binding.vpFeatured.adapter = adapter
        binding.indicator.setViewPager(binding.vpFeatured)

        runnable = object : Runnable {
            override fun run() {
                // Check if binding is null to avoid crash if view is destroyed but runnable executes
                if (_binding == null) return
                val count = binding.vpFeatured.adapter?.itemCount ?: 0
                if (count > 0) {
                    val next = (binding.vpFeatured.currentItem + 1) % count
                    binding.vpFeatured.currentItem = next
                }
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable!!, 3000)
    }

    private fun setupCategoryRecyclerView() {
        val categories = listOf(
            Category(1, "Full App\nDevelopment", "~ 1260 Prompts",
                R.drawable.ic_code_white, R.drawable.category_bg_full_app,
                route = "full_app_development"),
            Category(2, "Website\nBuilding",     "~ 1500 Prompts",
                R.drawable.ic_web_white,  R.drawable.category_bg_web,
                route = "website_building"),
            Category(3, "Creative\nWriting",     "~ 900 Prompts",
                R.drawable.ic_edit_white, R.drawable.category_bg_creative,
                route = "creative_writing"),
            Category(4, "Graphic\nDesign",       "~ 1300 Prompts",
                R.drawable.ic_design_white, R.drawable.category_bg_design,
                route = "graphic_design"),
            Category(5, "Data\nAnalysis",        "~ 2300 Prompts",
                R.drawable.ic_chart_white, R.drawable.category_bg_data,
                route = "data_analysis"),
            Category(6, "Marketing\nStrategy",   "~ 1200 Prompts",
                R.drawable.ic_marketing_white, R.drawable.category_bg_marketing,
                route = "marketing_strategy")
        )

        val adapter = CategoryAdapter(categories) { category ->
            val action = HomeFragmentDirections
                .actionHomeFragmentToCategoryPromptsFragment(category.route)
            findNavController().navigate(action)
        }

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCategories.adapter = adapter
    }

    private fun setupPopularPrompts() {
        val prompts = listOf(
            Prompt(1, "Complete Fitness App Prompt", "Category",
                "Target AI: ChatGPT", 4.9f, "4k", R.drawable.prompt_fitness, true),
            Prompt(2, "Generate eCommerce Website Code", "Category",
                "Target AI: Midjourney", 4.9f, "4k", R.drawable.prompt_ecommerce, true)
        )

        val adapter = PromptAdapter(prompts) { prompt ->
            val action = HomeFragmentDirections.actionHomeFragmentToPromptDetailFragment(prompt.id)
            findNavController().navigate(action)
        }

        binding.rvPopularPrompts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPopularPrompts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        runnable?.let { handler.removeCallbacks(it) }
        _binding = null
    }
}
