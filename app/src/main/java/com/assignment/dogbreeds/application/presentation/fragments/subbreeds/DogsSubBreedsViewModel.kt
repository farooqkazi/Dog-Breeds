package com.assignment.dogbreeds.application.presentation.fragments.subbreeds

import android.util.Log
import androidx.lifecycle.*
import com.assignment.dogbreeds.application.domain.model.Result
import com.assignment.dogbreeds.application.domain.repository.BreedsRepository
import com.assignment.dogbreeds.application.domain.repository.FavouriteRepository
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsSubBreedsViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository,
    private val favouriteRepository: FavouriteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val breed = SubBreedsFragmentArgs.fromSavedStateHandle(savedStateHandle).breed

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        loadSubBreeds()
        checkFavourite()
    }

    private fun checkFavourite() = viewModelScope.launch {
        val favourite = favouriteRepository.isFavourite(breed)
        Log.d("subViewModel ", "is fav: $favourite")
        _isFavourite.value = favourite
    }

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    fun toggleFavourite() = viewModelScope.launch {
        favouriteRepository.toggleFavourite(breed)
        val favourite = favouriteRepository.isFavourite(breed)
        Log.d("subViewModel ", "is fav: $favourite")
        _isFavourite.value = favourite
    }

    fun loadSubBreeds() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result: Result<List<Breed>> = breedsRepository.getSubBreeds(breed.name)
            _uiState.value = when (result) {
                is Result.Success -> UiState.Success(result.data!!)
                is Result.Loading -> UiState.Loading
                is Result.Error -> UiState.Error(result.errorType?.toString() ?: "Unknown Error")
            }

        }

    }

    sealed class UiState {
        object Loading : UiState()
        class Error(val msg: String) : UiState()
        class Success(val cakes: List<Breed>) : UiState()
    }
}