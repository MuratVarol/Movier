package com.varol.movier.remote

/**
 * Cascade class of all API response
 * Success if incoming data is valid
 * Error if incoming data is non-parcelable or Exception from service
 */
sealed class DataHolder<out T> {
    data class Success<out T>(val data: T) : DataHolder<T>()
    data class Error<out T>(val error: Throwable) : DataHolder<T>()
}