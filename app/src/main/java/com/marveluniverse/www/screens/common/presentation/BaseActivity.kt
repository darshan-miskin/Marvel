package com.marveluniverse.www.screens.common.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.marveluniverse.www.screens.common.ScreensNavigator
import com.marveluniverse.www.screens.common.TAG_HASH
import com.marveluniverse.www.screens.common.TAG_LIFECYCLE
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity<T: ViewDataBinding>(
    @LayoutRes val layout: Int
) : AppCompatActivity(){

    protected lateinit var binding: T
    @Inject lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)

        Log.d(TAG_HASH, "screenNavigatorHashCode: ${screensNavigator.hashCode()}")
    }

    fun showToast(string: String){
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}