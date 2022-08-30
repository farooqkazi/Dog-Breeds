package com.assignment.dogbreeds.application.presentation.fragments.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.dogbreeds.application.domain.repository.FavouriteRepository
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouriteRepository: FavouriteRepository,
) : ViewModel() {
    fun toggleFavourite(breed: Breed) = viewModelScope.launch {
        favouriteRepository.toggleFavourite(breed)
    }

    val favourites: Flow<List<Breed>> = flow {
        val fav: Flow<List<Breed>> = favouriteRepository.getFavourites()
        emitAll(fav)
    }.shareIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(), replay = 1)


}