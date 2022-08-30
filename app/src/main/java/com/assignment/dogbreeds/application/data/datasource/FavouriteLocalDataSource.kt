package com.assignment.dogbreeds.application.data.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import com.assignment.dogbreeds.application.domain.FavouriteDataSource
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteLocalDataSource @Inject constructor() : FavouriteDataSource {
    private val favourites = MutableStateFlow<List<Breed>>(emptyList())
    override suspend fun getFavourites(): Flow<List<Breed>> {
        return favourites
    }

    override suspend fun isFavourite(breed: Breed): Boolean {
        return favourites.value.contains(breed)
    }

    override suspend fun addFavourite(breed: Breed) {
        val newVal = favourites.value.toMutableList()
        newVal.add(breed)
        favourites.value = newVal.toList()
        Log.d("favdatasource", "size: ${favourites.value.size}")
    }

    override suspend fun removeFavourite(breed: Breed) {
        val newVal = favourites.value.toMutableList()
        newVal.remove(breed)
        favourites.value = newVal.toList()
        Log.d("favdatasource", "size: ${favourites.value.size}")
    }

}