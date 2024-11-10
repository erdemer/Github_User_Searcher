package com.example.githubusersearcher.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubusersearcher.databinding.FragmentFavoritesBinding
import com.example.githubusersearcher.presentation.userList.UserListAdapter
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarFavorite.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        observeAsEvents(viewModel.state) {
            binding.progressBarFavorites.visibility = if (it.isLoading) View.VISIBLE else View.GONE
            setAdapter(it.users)
        }
    }

    private fun setAdapter(models: List<UserUIModel>) {
        val adapter = UserListAdapter(onClick = {}, onFavoriteClick = {})
        adapter.submitList(models)
        binding.rvFavorites.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
