package com.varol.movier.di

import android.content.Context
import com.varol.movier.R
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import java.util.*

/**
 * appModule definitions for dependency injection
 *
 * This part will consist of injection Application based objects
 */
val appModule = module {
    factory("lang_code") { getLocaleLanguage() }
    single("api_key") { getApiKey(androidContext()) }
}

/**
 * returns country code Example: "us" for United States
 */
fun getLocaleLanguage() = Locale.getDefault().country

/**
 * returns defined API Key
 */
fun getApiKey(context: Context): String = context.getString(R.string.API_KEY_TMDB)