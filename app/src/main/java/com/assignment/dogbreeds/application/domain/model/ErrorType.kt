package com.assignment.dogbreeds.application.domain.model

sealed class ErrorType {
    object NetworkError : ErrorType()
    object ServerError : ErrorType()
    object UnknownError : ErrorType()
    object DataError : ErrorType()
}