package com.varol.movier.util.binding_adapters

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("setRating")
fun setRating(view: RatingBar, rating: Double) {
    if (view.rating.toDouble() != rating) {
        view.rating = rating.toFloat()
    }
}