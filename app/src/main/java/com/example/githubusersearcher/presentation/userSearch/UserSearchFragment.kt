package com.example.githubusersearcher.presentation.userSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubusersearcher.R
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.databinding.FragmentUserSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserSearchFragment: Fragment() {
    private var _binding: FragmentUserSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        focusSearchView()
        binding.svGithubUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    println("Submitted query: $it")
                    val bundle = Bundle().apply {
                        putString(Constants.ARG_SEARCH_KEYWORD, it)
                    }
                    findNavController().navigate(R.id.action_userSearchFragment_to_userListFragment, bundle)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    /**
     * Expands and focuses the search view after a short delay for better UX.
     * Uses lifecycleScope for safety and Dispatchers.Main for UI thread.
     */
    private fun focusSearchView() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(500)
            binding.svGithubUser.isIconified = false
            binding.svGithubUser.requestFocus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}