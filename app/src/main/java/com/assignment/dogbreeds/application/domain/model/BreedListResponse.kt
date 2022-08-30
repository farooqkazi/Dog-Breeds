package com.assignment.dogbreeds.application.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class BreedListResponse(

    @SerializedName("message")
    @Expose
    var message: Map<String,List<String>>,

    @SerializedName("status")
    @Expose
    var status: String
)