package com.example.githubusersearcher.presentation.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.githubusersearcher.data.model.search.Item
import com.example.githubusersearcher.databinding.FragmentUserListBinding
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment: Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAsEvents(viewModel.state){
            if(it.isLoading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
            setAdapter(it.users)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun UserListFragment.setAdapter(items: List<Item>) {
        val adapter = UserListAdapter()
        adapter.submitList(items)
        binding.rvUsers.adapter = adapter
    }
}

