package com.assignment.dogbreeds.application.presentation.fragments.breedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.dogbreeds.application.domain.model.Result
import com.assignment.dogbreeds.application.domain.repository.BreedsRepository
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsBreedsListViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        loadBreeds()
    }

    fun loadBreeds() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val result: Result<List<Breed>> = breedsRepository.getBreeds()
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