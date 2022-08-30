package com.assignment.dogbreeds.application.data.api

import com.assignment.dogbreeds.application.domain.model.BreedListResponse
import com.assignment.dogbreeds.application.domain.model.ImageResponse
import com.assignment.dogbreeds.application.domain.model.SubBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsApi {

    @GET(BREEDS_LIST_ENDPOINT)
    suspend fun getBreedsList(): Response<BreedListResponse>

    @GET(RANDOM_BREED_IMAGE_URL)
    suspend fun getRandomImage(): Response<ImageResponse>

    @GET("api/breed/{breed}/list")
    suspend fun getSubBreeds(@Path("breed") breed: String): Response<SubBreedResponse>

}