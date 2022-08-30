package com.assignment.dogbreeds.application.data.repositories

import com.assignment.dogbreeds.application.data.api.DogBreedsApi
import com.assignment.dogbreeds.application.data.api.RequestApiCall
import com.assignment.dogbreeds.application.domain.model.Result
import com.assignment.dogbreeds.application.domain.repository.BreedsRepository
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import kotlinx.coroutines.*
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val dogsbreedApi: DogBreedsApi,
    private val requestApiCall: RequestApiCall
) : BreedsRepository {

    override suspend fun getBreeds(): Result<List<Breed>> {
        val res = requestApiCall.requestApiCall { dogsbreedApi.getBreedsList() }
        val finalBreeds = mutableListOf<Breed>()
        if (res is Result.Success && res.data != null) {
            val defferredImages = mutableListOf<Deferred<String?>>()
            repeat(5) {
                withContext(Dispatchers.Default) {
                    defferredImages.add(async {
                        val imgRes = requestApiCall.requestApiCall { dogsbreedApi.getRandomImage() }
                        if (imgRes is Result.Success && imgRes.data != null) {
                            imgRes.data.message
                        } else null
                    })
                }
            }
            val images = defferredImages.awaitAll().filterNotNull()
            res.data.message.forEach {
                finalBreeds.add(
                    Breed(
                        it.key,
                        images.random(),
                        it.value.size
                    )
                )
            }
            return Result.Success(finalBreeds)
        }
        return Result.Error(res.errorType)
    }

    override suspend fun getSubBreeds(name: String): Result<List<Breed>> {
        val res = requestApiCall.requestApiCall { dogsbreedApi.getSubBreeds(name) }
        val finalBreeds = mutableListOf<Breed>()
        if (res is Result.Success && res.data != null) {
            val defferredImages: List<String> = res.data.message.map {
                withContext(Dispatchers.Default) {
                    async {
                        val imgRes = requestApiCall.requestApiCall { dogsbreedApi.getRandomImage() }
                        if (imgRes is Result.Success && imgRes.data != null) {
                            imgRes.data.message
                        } else null
                    }
                }
            }.awaitAll().filterNotNull()
            res.data.message.forEach {
                finalBreeds.add(Breed(it, defferredImages.random()))
            }
            return Result.Success(finalBreeds)
        }
        return Result.Error(res.errorType)
    }

}