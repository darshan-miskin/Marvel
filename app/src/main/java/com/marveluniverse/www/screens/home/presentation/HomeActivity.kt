package com.marveluniverse.www.screens.home.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.marveluniverse.www.R
import com.marveluniverse.www.databinding.ActivityHomeBinding
import com.marveluniverse.www.screens.common.presentation.BaseActivity
import com.marveluniverse.www.screens.common.presentation.gone
import com.marveluniverse.www.screens.common.presentation.visible
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home),
    CharactersListAdapter.Listener {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = CharactersListAdapter(this, this)
        binding.adapter = adapter

        adapter.addLoadStateListener {
            when(it.append){
                is LoadState.Error -> binding.pbRvLoading.gone()
                LoadState.Loading -> binding.pbRvLoading.visible()
                is LoadState.NotLoading -> binding.pbRvLoading.gone()
            }
            when(it.refresh){
                is LoadState.Error -> {}
                LoadState.Loading -> {
                    binding.pbPageLoading.visible()
                    binding.rvMain.gone()}
                is LoadState.NotLoading -> {
                    binding.pbPageLoading.gone()
                    binding.rvMain.visible()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.getCharactersPaging().collect {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onCharacterClick(
        characterModel: CharacterModel,
        imageView: ImageView,
        textView: TextView
    ) {
        screensNavigator.toCharacterDetailsScreen(characterModel)
    }
}