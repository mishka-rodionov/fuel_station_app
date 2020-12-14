package com.rodionov.oktan.app.platform

sealed class Failure {
    object AuthError : Failure()
}