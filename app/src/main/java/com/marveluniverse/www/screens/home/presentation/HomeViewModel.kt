package com.marveluniverse.www.screens.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marveluniverse.www.screens.common.TAG_LIFECYCLE
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.domain.response.State
import com.marveluniverse.www.screens.home.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val charactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _charactersLiveData = MutableLiveData<Result>()
    val charactersLiveData: LiveData<Result> get() = _charactersLiveData
    var isPageEnd = false
    var isLoading = false

    init {
        getCharacters(State.REFRESH)
    }

    fun getCharacters(loadingState: State) = viewModelScope.launch {
        _charactersLiveData.postValue(Result.Loading(loadingState))
        val result = charactersUseCase.getCharacters()
        if(result is Result.Success){
            Timber.d("result.response.size: ${result.response.size}")
            isPageEnd = result.response.size == 0
        }
        _charactersLiveData.postValue(result)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.tag(TAG_LIFECYCLE).i("_charactersLiveData: $_charactersLiveData")
        Timber.tag(TAG_LIFECYCLE).i("charactersLiveData: $charactersLiveData")
    }
}