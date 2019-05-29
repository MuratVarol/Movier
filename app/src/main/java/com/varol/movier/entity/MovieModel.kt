package com.varol.movier.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.varol.movier.BASE_IMAGE_LINK
import kotlinx.android.parcel.Parcelize

@Parcelize
class MoviesModel(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("video")
    val isVideo: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("genre_ids")
    val genreIdList: List<Int?>?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("adult")
    val isAdultMovie: Boolean = false,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?

) : Parcelable {
    val posterFullPath: String
        get() = BASE_IMAGE_LINK.plus(posterPath)
    val backDropFullPath: String
        get() = BASE_IMAGE_LINK.plus(backdropPath)
}


/**
"vote_count": 60,
"id": 505954,
"video": false,
"vote_average": 4.5,
"title": "T-34",
"popularity": 313.141,
"poster_path": "/AnLCqcH66Ecu8VFmXu3RofK4SMK.jpg",
"original_language": "ru",
"original_title": "Т-34",
"genre_ids": [10752, 18, 12, 28],
"backdrop_path": "/59qyrVGLLtrcuFtJGdixzn7H4OI.jpg",
"adult": false,
"overview": "1941 - WWii. the second lieutenant Nikolai ivushkin, commander of a t-34, engages in an unequal battle against the tank ace Klaus Jager in a battle near moscow. His mission is more of a suicide - to destroy a dozen german tanks, all by himself. that said, luck does favour the bold. He wins the battle, barely survives, but loses his tank and lands himself in captivity for three long years... there was little to no chance for ivushkin and Jager to meet again, but the war knows how to throw a curve ball.in the spring of 1944, the Wehrmacht commands Jager to take charge of the ohrdruf  re range and turn it into a training center for elite german armored forces, using the latest t-34 as a running target. this is how Jager and ivushkin cross paths again. Jager o ers ivushkin to become the commander of a legendary tank and pick his crew from fellow camp prisoners. Nothing goes according to plan, though, when ivushkin uses exercises for a daring and carefully planned escape.",
"release_date": "2018-12-27"
 */