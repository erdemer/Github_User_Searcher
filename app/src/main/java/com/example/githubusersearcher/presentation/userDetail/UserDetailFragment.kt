package com.example.githubusersearcher.presentation.userDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.githubusersearcher.data.model.detail.UserDetailResponse
import com.example.githubusersearcher.databinding.FragmentUserDetailBinding
import com.example.githubusersearcher.util.ext.observeAsEvents
import dagger.hilt.android.AndroidEntryPoint
import com.example.githubusersearcher.R
import com.example.githubusersearcher.presentation.userDetail.uiModel.UserDetailUIModel
import com.example.githubusersearcher.presentation.userList.uiModel.UserUIModel

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarUserDetail.setOnClickListener {
            findNavController().popBackStack()
        }
        observeAsEvents(viewModel.state) {
            binding.progressBarDetail.isVisible = it.isLoading
            setUserDetailUI(it.user)
        }
    }

    private fun setUserDetailUI(response: UserDetailUIModel?) {
        with(binding) {
            ivFavorite.setImageResource(
                if (response?.isFavorite == true) {
                    R.drawable.baseline_favorite_24
                } else {
                    R.drawable.baseline_favorite_border_24
                }
            )
            ivFavorite.setOnClickListener {
                viewModel.manageFavorite(response)
            }
            ivUserDetailProfileIcon.load(response?.avatarUrl)
            setCardsInfo(response)

            response?.favoriteAdditionDate?.let {
                tvFavoriteDate.text = context?.getString(R.string.favorite_date, it)
            } ?: run {
                tvFavoriteDate.text = context?.getString(R.string.favorite_date, "-")
            }
        }
    }

    private fun setCardsInfo(response: UserDetailUIModel?) {
        with(binding) {
            response?.name?.let {
                tvName.text = context?.getString(R.string.user_detail_name, response.name)
            } ?: run {
                tvName.text = context?.getString(R.string.user_detail_name, "-")
            }
            response?.bio?.let {
                tvBio.text = context?.getString(R.string.user_detail_bio, response.bio)
            } ?: run {
                tvBio.text = context?.getString(R.string.user_detail_bio, "-")
            }
            response?.location?.let {
                tvLocation.text =
                    context?.getString(R.string.user_detail_location, response.location)
            } ?: run {
                tvLocation.text = context?.getString(R.string.user_detail_location, "-")
            }
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