package com.assignment.dogbreeds.application.data.datasource

import android.util.Log
import com.assignment.dogbreeds.application.domain.FavouriteDataSource
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteLocalDataSource @Inject constructor() : FavouriteDataSource {
    private val _favourites = MutableStateFlow<List<Breed>>(emptyList())
    override suspend fun getFavourites(): Flow<List<Breed>> {
        return _favourites
    }

    override suspend fun isFavourite(breed: Breed): Boolean {
        return _favourites.value.contains(breed)
    }

    override suspend fun addFavourite(breed: Breed) {
        val newVal = _favourites.value.toMutableList()
        newVal.add(breed)
        _favourites.value = newVal.toList()
        Log.d("favdatasource", "size: ${_favourites.value.size}")
    }

    override suspend fun removeFavourite(breed: Breed) {
        val newVal = _favourites.value.toMutableList()
        newVal.remove(breed)
        _favourites.value = newVal.toList()
        Log.d("favdatasource", "size: ${_favourites.value.size}")
    }

}