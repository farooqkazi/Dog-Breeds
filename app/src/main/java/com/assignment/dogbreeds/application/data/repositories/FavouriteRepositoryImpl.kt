package com.assignment.dogbreeds.application.data.repositories

import com.assignment.dogbreeds.application.domain.FavouriteDataSource
import com.assignment.dogbreeds.application.domain.repository.FavouriteRepository
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
   private val dataSource: FavouriteDataSource
) : FavouriteRepository {
    override suspend fun getFavourites(): Flow<List<Breed>> {
        return dataSource.getFavourites()
    }

    override suspend fun isFavourite(breed: Breed): Boolean {
        return dataSource.isFavourite(breed)
    }

    override suspend fun toggleFavourite(breed: Breed): Boolean {
        return if (dataSource.isFavourite(breed)) {
            dataSource.removeFavourite(breed)
            false
        } else {
            dataSource.addFavourite(breed)
            true
        }
    }
}
