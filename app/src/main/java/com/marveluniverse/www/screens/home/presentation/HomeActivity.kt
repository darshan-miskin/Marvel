package com.marveluniverse.www.screens.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.marveluniverse.www.R
import com.marveluniverse.www.databinding.ActivityHomeBinding
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.presentation.BaseActivity
import com.marveluniverse.www.screens.common.presentation.gone
import com.marveluniverse.www.screens.common.presentation.visible
import com.marveluniverse.www.screens.details.presentation.CharacterDetailsActivity
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home),
    CharactersListAdapter.Listener {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = CharactersListAdapter(this, this)
        binding.adapter = adapter


        lifecycleScope.launch {
            viewModel.getCharactersPaging().collect {
                Timber.d(it.toString())
                binding.pbPageLoading.gone()
                binding.pbRvLoading.gone()
                binding.rvMain.visible()
                adapter.submitData(lifecycle, it)
            }
        }

        binding.rvMain.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    binding.pbRvLoading.visible()
//                    viewModel.getCharacters()
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