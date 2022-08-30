package com.assignment.dogbreeds.application.presentation.uimodels

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val name: String,
    val image: String?=null,
    val noOfSubBreeds: Int = 0
) : Parcelable