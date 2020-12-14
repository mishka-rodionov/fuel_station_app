package com.rodionov.oktan.app.platform

import com.google.gson.Gson
import retrofit2.HttpException

class ErrorHandler(
    private val gson: Gson
) {

    fun proceedException(exception: Throwable): Failure {
        when {
            exception is HttpException -> {
                try {
//                    val error = gson.fromJson(exception.response()?.errorBody()?.string(), ErrorResponse::class.java)
//                    return when (error.error) {
//                    }
                } catch (e: Exception) {
                }
            }
        }

        return Failure.AuthError
    }

}