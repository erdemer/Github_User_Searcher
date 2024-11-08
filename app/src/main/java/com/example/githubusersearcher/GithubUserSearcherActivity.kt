package com.example.githubusersearcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusersearcher.databinding.ActivityGithubUserSearcherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUserSearcherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubUserSearcherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubUserSearcherBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}