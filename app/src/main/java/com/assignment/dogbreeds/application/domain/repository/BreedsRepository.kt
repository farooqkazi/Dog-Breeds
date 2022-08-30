package com.assignment.dogbreeds.application.domain.repository

import com.assignment.dogbreeds.application.domain.model.Result
import com.assignment.dogbreeds.application.presentation.uimodels.Breed

interface BreedsRepository {
    suspend  fun getBreeds(): Result<List<Breed>>
    suspend  fun getSubBreeds(name: String): Result<List<Breed>>
}