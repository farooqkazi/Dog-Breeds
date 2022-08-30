package com.assignment.dogbreeds.application.domain.model

interface ErrorTypeHandler {
    fun getError(exception: Exception): ErrorType
}