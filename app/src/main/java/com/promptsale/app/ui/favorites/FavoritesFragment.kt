package com.promptsale.app.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.promptsale.app.R
import com.promptsale.app.adapters.FavoriteAdapter
import com.promptsale.app.databinding.FragmentFavoritesBinding
import com.promptsale.app.models.Prompt

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val favorites = listOf(
            Prompt(1, "Generate E-Commerce Website Code", "Category",
                "Target AI: Midjourney", 4.8f, "4k", R.drawable.prompt_ecommerce, true),
            Prompt(2, "Build a Full Social Media App", "Category",
                "Target AI: ChatGPT", 4.8f, "5.2k", R.drawable.prompt_social, true),
            Prompt(3, "Master Content Writing Pack", "Category",
                "Target AI: ChatGPT", 4.8f, "3k", R.drawable.prompt_content, true),
            Prompt(4, "Data Visualization Dashboard", "Category",
                "Target AI: ChatGPT", 4.8f, "2k", R.drawable.prompt_data, true)
        )

        val adapter = FavoriteAdapter(favorites) { prompt ->
            val action = FavoritesFragmentDirections
                .actionFavoritesFragmentToPromptDetailFragment(prompt.id)
            findNavController().navigate(action)
        }

        if (favorites.isEmpty()) {
            binding.emptyFavoritesState.visibility = View.VISIBLE
            binding.rvFavorites.visibility = View.GONE
        } else {
            binding.emptyFavoritesState.visibility = View.GONE
            binding.rvFavorites.visibility = View.VISIBLE
            binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
            binding.rvFavorites.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
