package com.varol.movier.extension

import io.reactivex.Single
import io.reactivex.exceptions.CompositeException


/**
 * RxJava hides inner exception on console;
 * this extension using for better error logging for child observers
 */
fun <T> Single<T>.dropBreadcrumb(): Single<T> {
    val breadcrumb = BreadcrumbException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, breadcrumb)
    }
}

class BreadcrumbException : Exception()