package com.assignment.dogbreeds.application.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("message")
    @Expose
    var message: String,

    @SerializedName("status")
    @Expose
    var status: String
)