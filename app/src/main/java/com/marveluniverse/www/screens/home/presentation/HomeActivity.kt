package com.marveluniverse.www.screens.home.presentation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.marveluniverse.www.R
import com.marveluniverse.www.databinding.ActivityHomeBinding
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.domain.response.State
import com.marveluniverse.www.screens.common.presentation.BaseActivity
import com.marveluniverse.www.screens.common.presentation.BindingActivity
import com.marveluniverse.www.screens.common.presentation.gone
import com.marveluniverse.www.screens.common.presentation.visible
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home),
    CharactersListAdapter.Listener {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = CharactersListAdapter(this, this)
        binding.adapter = adapter

        viewModel.charactersLiveData.observe(this) {
            when(it){
                is Result.Loading -> {
                    when(it.state){
                        State.REFRESH -> {
                            binding.pbPageLoading.visible()
                            binding.pbRvLoading.gone()
                            binding.rvMain.gone()
                        }
                        State.LIST_APPEND -> {
                            binding.pbRvLoading.visible()
                        }
                    }
                }
                is Result.Success -> {
                    binding.pbPageLoading.gone()
                    binding.pbRvLoading.gone()
                    binding.rvMain.visible()
                    val list: ArrayList<CharacterModel> = it.getResponse()
                    list.addAll(0, adapter.currentList)
                    adapter.submitList(list)
                }
                Result.Failure -> {
                    showToast(getString(R.string.something_went_wrong))
                }
            }
        }

        binding.rvMain.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getCharacters(State.LIST_APPEND)
                }
            }
        })
    }

    override fun onCharacterClick(
        characterModel: CharacterModel,
        imageView: ImageView,
        textView: TextView
    ) {
        screensNavigator.toCharacterDetailsScreen(characterModel)
    }
}