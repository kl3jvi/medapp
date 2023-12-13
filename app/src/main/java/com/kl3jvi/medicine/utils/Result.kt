package com.kl3jvi.medicine.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return map<T, Result<T>> {
        Result.Success(it)
    }.catch { exception ->
        emit(Result.Error(exception))
    }.onStart { emit(Result.Loading) }
}