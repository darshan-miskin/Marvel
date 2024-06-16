package com.marveluniverse.www.screens.common.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marveluniverse.www.screens.common.ScreensNavigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(){

    @Inject lateinit var screensNavigator: ScreensNavigator

    fun showToast(string: String){
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}