package com.example.githubusersearcher.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubusersearcher.R
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.common.error.ErrorDialog
import com.example.githubusersearcher.databinding.FragmentFavoritesBinding
import com.example.githubusersearcher.presentation.userList.UserListAdapter
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarFavorite.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        observeAsEvents(viewModel.state) { state ->
            binding.progressBarFavorites.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            if (state.users.isEmpty()) {
                binding.rvFavorites.visibility = View.GONE
                binding.groupNoData.visibility = View.VISIBLE
            } else {
                binding.rvFavorites.visibility = View.VISIBLE
                binding.groupNoData.visibility = View.GONE
            }
            if (state.error.isNotEmpty()) {
                ErrorDialog().show(childFragmentManager, ErrorDialog.TAG)
            }
            setAdapter(state.users)
        }
    }


    private fun setAdapter(models: List<UserUIModel>) {
        val adapter = UserListAdapter(onClick = { user, position ->
            val bundle = Bundle().apply {
                putString(Constants.ARG_USER_NAME, user.name)
            }
            findNavController().navigate(
                R.id.action_favoritesFragment_to_userDetailFragment,
                bundle
            )
        }, onFavoriteClick = { viewModel.removeFavorite(it) })
        adapter.submitList(models)
        binding.rvFavorites.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavorites()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
