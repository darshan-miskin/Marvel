package com.marveluniverse.www.screens.common

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.marveluniverse.www.screens.details.presentation.CharacterDetailsActivity
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import javax.inject.Inject

class ScreensNavigator @Inject constructor(activity: Activity) {
    private val activity: AppCompatActivity = activity as AppCompatActivity

    fun navigateBack(){
        activity.onBackPressedDispatcher
    }

    fun toCharacterDetailsScreenAnimated(
        characterModel: CharacterModel,
        imageView: ImageView,
        textView: TextView
    ) {
        CharacterDetailsActivity.startWithAnimation(
            activity,
            characterModel,
            imageView,
            textView
        )
    }

    fun toCharacterDetailsScreen(characterModel: CharacterModel){
        CharacterDetailsActivity.start(activity, characterModel)
    }
}