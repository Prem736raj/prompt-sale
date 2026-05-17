package com.promptsale.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.promptsale.app.databinding.FragmentPromptDetailBinding
import com.promptsale.app.databinding.ItemPromptStructureBinding

class PromptDetailFragment : Fragment() {

    private var _binding: FragmentPromptDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromptDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupTabs()
        setupStructureSections()
        setupCopyButton()
    }

    private fun setupToolbar() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivFavorite.setOnClickListener {
            Toast.makeText(requireContext(), "Added to Favorites!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("DESCRIPTION"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("PROMPT STRUCTURE"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("REVIEWS"))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupStructureSections() {
        val sections = listOf(
            "Section 1: App Architecture & Tech Stack",
            "Section 2: Database Schema (SQL/NoSQL)",
            "Section 3: API Endpoints (REST/GraphQL)",
            "Section 4: Front-end UI Components"
        )

        sections.forEach { section ->
            val sectionBinding = ItemPromptStructureBinding.inflate(
                layoutInflater, binding.llPromptStructure, false
            )
            sectionBinding.tvStructureItem.text = section
            binding.llPromptStructure.addView(sectionBinding.root)
        }
    }

    private fun setupCopyButton() {
        binding.btnCopyPrompt.setOnClickListener {
            val clipboard = requireActivity()
                .getSystemService(android.content.Context.CLIPBOARD_SERVICE)
                    as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Prompt",
                binding.tvDescription.text.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Prompt copied!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
