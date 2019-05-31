package com.varol.movier.viewentity

import com.varol.movier.model.MoviesModel
import com.varol.movier.remote.MovieTypes
import com.varol.movier.util.listener.ItemClickListener

class MoviesWithType(
    val type: MovieTypes,
    val movies: MutableList<MoviesModel>?,
    val itemClickListener: ItemClickListener<MoviesModel>?

) {
    val getType: String
        get() = when (type) {
            is MovieTypes.Popular -> "Popular"
            is MovieTypes.TopRated -> "Top Rated"
            is MovieTypes.Revenue -> "Top Sell"
        }

}