package com.george.codingexercise.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.george.codingexercise.databinding.ActivityMainBinding
import com.george.codingexercise.ui.main.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.getList()
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {

        mainAdapter = MainAdapter()

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collect {
                    when (it) {
                        MainState.Loading -> loadingState()
                        is MainState.Error -> errorState(it)
                        is MainState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(state: MainState.Success) {
        binding.pbMain.isVisible = false
        mainAdapter.updateList(state.itemsList)
    }

    private fun errorState(state: MainState.Error) {
        binding.pbMain.isVisible = false
        binding.tvMainError.text = state.error
    }

    private fun loadingState() {
        binding.pbMain.isVisible = true
    }


}