package com.marveluniverse.www.screens.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marveluniverse.www.screens.common.TAG_LIFECYCLE
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.domain.response.State
import com.marveluniverse.www.screens.home.domain.GetCharactersUseCase
import com.marveluniverse.www.screens.home.domain.response.charactermodels.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val uiState = object {
        var isPagingEnd = false
        var isLoading = false
        fun isValid() = !isPagingEnd && !isLoading
    }

    private val _charactersLiveData: MutableLiveData<Result> = MutableLiveData()
    val charactersLiveData: LiveData<Result> get() = _charactersLiveData

    init {
        getCharacters(State.REFRESH)
    }

    fun getCharacters(loadingState: State) = viewModelScope.launch {
        if(uiState.isValid()) {
            uiState.isLoading = true
            _charactersLiveData.postValue(Result.Loading(loadingState))
            val result = charactersUseCase.getCharacters()
            if (result is Result.Success) {
                Timber.d("result.response.size: ${result.data.size}")
                uiState.isPagingEnd = result.data.isEmpty()
            }
            uiState.isLoading = false
            _charactersLiveData.postValue(result)
        }
    }
}