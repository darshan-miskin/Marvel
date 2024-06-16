package com.marveluniverse.www.screens.details.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.marveluniverse.www.R
import com.marveluniverse.www.databinding.ActivityCharacterDetailsBinding
import com.marveluniverse.www.screens.common.presentation.BaseActivity
import com.marveluniverse.www.screens.common.presentation.BindingActivity
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailsActivity :
    BindingActivity<ActivityCharacterDetailsBinding>(R.layout.activity_character_details) {

//    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var characterModel: CharacterModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterModel = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(INTENT_CHARACTER_MODEL) as CharacterModel
        else
            intent.getSerializableExtra(INTENT_CHARACTER_MODEL, CharacterModel::class.java)!!

        binding.character = characterModel

//        viewModel.comicsLiveData.observe(this){
//
//        }
    }

    companion object {
        private const val INTENT_CHARACTER_MODEL = "character_model"

        fun startWithAnimation(
            activity: AppCompatActivity,
            characterModel: CharacterModel,
            imageView: ImageView,
            textView: TextView
        ) {
            val options = ViewCompat.getTransitionName(imageView)?.let { imageName ->
                ViewCompat.getTransitionName(textView)?.let { textName ->
                    val list = arrayOf(
                        Pair<View, String>(imageView, imageName),
                        Pair<View, String>(textView, textName)
                    )
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        *list
                    )
                }
            }
            start(activity, characterModel, options)
        }

        fun start(context: Context, characterModel: CharacterModel, options: ActivityOptionsCompat?=null) {
            val intent = Intent(context, CharacterDetailsActivity::class.java)
            intent.putExtra(INTENT_CHARACTER_MODEL, characterModel)
            context.startActivity(intent, options?.toBundle())
        }
    }
}