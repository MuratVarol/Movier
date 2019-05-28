package com.varol.movier.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.varol.movier.R
import com.varol.movier.util.GlideApp

fun ImageView.setImageByUrl(url: String?) {
    if (url?.isNotEmpty() == true) {

        val circularProgressDrawable = CircularProgressDrawable(this.context)
        circularProgressDrawable.apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

        GlideApp.with(this.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(this)
    } else {
        GlideApp.with(this.context)
            .load(R.drawable.ph_poster)
            .into(this)
    }
}