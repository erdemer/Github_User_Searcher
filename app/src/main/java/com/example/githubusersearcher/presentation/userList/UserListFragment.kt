package com.example.githubusersearcher.presentation.userList

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubusersearcher.R
import com.example.githubusersearcher.common.Constants
import com.example.githubusersearcher.databinding.FragmentUserListBinding
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_userListFragment_to_favoritesFragment)
        }
    }

    private fun setAdapter(items: List<UserUIModel>) {
        val adapter = UserListAdapter(
            onClick = { user ->
                val bundle = Bundle().apply {
                    putString(Constants.ARG_USER_NAME, user.name)
                }
                findNavController().navigate(
                    R.id.action_userListFragment_to_userDetailFragment,
                    bundle
                )
            }, onFavoriteClick = {
                viewModel.addUserToFavorites(it)
            }
        )
        adapter.submitList(items)
        binding.rvUsers.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchKeyword?.let { viewModel.getUsers(it) }
        observeAsEvents(viewModel.state) {
            binding.progressBar.isVisible = it.isLoading
            setAdapter(it.users)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
