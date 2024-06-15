package com.marveluniverse.www.screens.home.presentation

import androidx.lifecycle.ViewModel
import com.marveluniverse.www.screens.home.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersUseCase: GetCharactersUseCase
) : ViewModel() {
    fun getCharactersPaging() = charactersUseCase.getCharactersPaging()
}