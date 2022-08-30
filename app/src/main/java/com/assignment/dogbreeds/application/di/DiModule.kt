package com.assignment.dogbreeds.application.di

import com.assignment.dogbreeds.application.data.api.DogBreedsApi
import com.assignment.dogbreeds.application.domain.model.ErrorTypeHandler
import com.assignment.dogbreeds.application.domain.model.ErrorTypeHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
class DiModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dog.ceo/")
            .build()
    }

    @Provides
    fun provideBreedListService(retrofit: Retrofit): DogBreedsApi {
        return retrofit.create(DogBreedsApi::class.java)
    }

    @Provides
    fun provideErrorTypeHandler(errorTypeHandlerImpl: ErrorTypeHandlerImpl): ErrorTypeHandler {
        return errorTypeHandlerImpl
    }

}
