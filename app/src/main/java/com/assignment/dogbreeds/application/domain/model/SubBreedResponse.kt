package com.assignment.dogbreeds.application.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubBreedResponse(
    @SerializedName("message")
    @Expose
    var message: List<String>,

    @SerializedName("status")
    @Expose
    var status: String
)