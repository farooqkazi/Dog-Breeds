package com.assignment.dogbreeds.application.domain

import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.flow.Flow

interface FavouriteDataSource {
    suspend fun getFavourites(): Flow<List<Breed>>
    suspend fun isFavourite(breed: Breed): Boolean
    suspend fun addFavourite(breed: Breed): Unit
    suspend fun removeFavourite(breed: Breed): Unit
}
