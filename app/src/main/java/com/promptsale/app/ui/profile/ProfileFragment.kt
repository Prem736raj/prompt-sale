package com.promptsale.app.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.promptsale.app.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.llMyCollections.setOnClickListener {
            Toast.makeText(requireContext(), "My Collections", Toast.LENGTH_SHORT).show()
        }
        binding.llDownloadHistory.setOnClickListener {
            Toast.makeText(requireContext(), "Download History", Toast.LENGTH_SHORT).show()
        }
        binding.llProfileSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Profile Settings", Toast.LENGTH_SHORT).show()
        }
        binding.llHelpSupport.setOnClickListener {
            Toast.makeText(requireContext(), "Help & Support", Toast.LENGTH_SHORT).show()
        }
        binding.llAppFeedback.setOnClickListener {
            Toast.makeText(requireContext(), "App Feedback", Toast.LENGTH_SHORT).show()
        }
        binding.llLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Logged Out", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
