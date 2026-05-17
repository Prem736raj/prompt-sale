package com.promptsale.app.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.promptsale.app.databinding.FragmentCategoryDetailBinding

class CategoryDetailFragment : Fragment() {

    private var _binding: FragmentCategoryDetailBinding? = null
    private val binding get() = _binding!!
    
    // Assuming safe args is used as in HomeFragment
    // private val args: CategoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Use arguments if available
        val categoryName = arguments?.getString("categoryName") ?: "Category"
        binding.tvCategoryTitle.text = categoryName
        
        binding.ivBack.setOnClickListener {
            // Handle back press or use findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
