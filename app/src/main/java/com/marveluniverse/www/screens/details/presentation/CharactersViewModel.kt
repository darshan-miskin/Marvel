package com.marveluniverse.www.screens.details.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marveluniverse.www.screens.common.domain.response.Result
import com.marveluniverse.www.screens.common.domain.response.State
import com.marveluniverse.www.screens.details.domain.GetComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getComicsUseCase: GetComicsUseCase
): ViewModel() {
    private val _comicsLiveData = MutableLiveData<Result>()
    val comicsLiveData get() = _comicsLiveData

    init {
        getComics(State.REFRESH)
    }

    private fun getComics(loadingState: State)  {
        viewModelScope.launch {
            _comicsLiveData.postValue(Result.Loading(loadingState))
            val result = getComicsUseCase.getComics()
            _comicsLiveData.postValue(result)
        }
    }

}