package com.marveluniverse.www.screens.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.marveluniverse.www.screens.common.TAG_LIFECYCLE
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.home.domain.GetCharactersUseCase
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _charactersLiveData = MutableLiveData<Result>()
    val charactersLiveData: LiveData<Result> get() = _charactersLiveData

    fun getCharactersPaging() = charactersUseCase.getCharactersPaging()

    override fun onCleared() {
        super.onCleared()
        Timber.tag(TAG_LIFECYCLE).i("_charactersLiveData: $_charactersLiveData")
        Timber.tag(TAG_LIFECYCLE).i("charactersLiveData: $charactersLiveData")
    }
}