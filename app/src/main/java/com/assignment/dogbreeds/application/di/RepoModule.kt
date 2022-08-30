package com.assignment.dogbreeds.application.di

import com.assignment.dogbreeds.application.data.datasource.FavouriteLocalDataSource
import com.assignment.dogbreeds.application.data.repositories.BreedsRepositoryImpl
import com.assignment.dogbreeds.application.data.repositories.FavouriteRepositoryImpl
import com.assignment.dogbreeds.application.domain.FavouriteDataSource
import com.assignment.dogbreeds.application.domain.repository.BreedsRepository
import com.assignment.dogbreeds.application.domain.repository.FavouriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun provideBreedsRepository(breedsRepositoryImpl: BreedsRepositoryImpl): BreedsRepository

    @Binds
    abstract fun provideFavoriteDatasource(favouriteLocalDataSource: FavouriteLocalDataSource): FavouriteDataSource

    @Binds
    abstract fun provideFavoriteRepository(favouriteRepositoryImpl: FavouriteRepositoryImpl): FavouriteRepository
}