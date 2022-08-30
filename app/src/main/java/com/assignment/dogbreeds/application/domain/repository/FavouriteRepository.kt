package com.assignment.dogbreeds.application.domain.repository

import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun getFavourites(): Flow<List<Breed>>
    suspend fun isFavourite(breed: Breed): Boolean
    suspend fun toggleFavourite(breed: Breed): Boolean
}