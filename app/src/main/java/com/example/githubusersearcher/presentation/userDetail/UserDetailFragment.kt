package com.example.githubusersearcher.presentation.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.databinding.FragmentUserDetailBinding
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint
import com.example.githubusersearcher.R

@AndroidEntryPoint
class UserDetailFragment: Fragment(){
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAsEvents(viewModel.state) {
            binding.progressBarDetail.isVisible = it.isLoading
            setUserDetailUI(it.user)
        }
    }

    private fun setUserDetailUI(response: UserDetailResponse?) {
        with(binding) {
            response?.name?.let {
                tvName.text = context?.getString(R.string.user_detail_name, response.name)
            }
            response?.bio?.let {
                tvBio.text = context?.getString(R.string.user_detail_bio, response.bio)
            }
            response?.location?.let {
                tvLocation.text = context?.getString(R.string.user_detail_location, response.location)
            }
            ivUserDetailProfileIcon.load(response?.avatarUrl)

            response?.followers?.let {
                tvFollowersValue.text = it.toString()
            } ?: run {
                tvFollowersValue.text = "0"
            }
            response?.following?.let {
               tvFollowingValue.text = it.toString()
            } ?: run {
                tvFollowingValue.text = "0"
            }
            response?.publicRepos?.let {
                tvRepoValue.text = it.toString()
            } ?: run {
                tvRepoValue.text = "0"
            }
        }
    }
}