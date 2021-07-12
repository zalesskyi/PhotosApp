package com.zalesskyi.photosapp.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.request.CachePolicy
import coil.size.Scale

const val imageItemSize = 1400

fun ImageView.image(image: String?,
                    @DrawableRes placeHolderResId: Int? = null) {
    load(image) {
        placeHolderResId?.let(::placeholder)
        size(imageItemSize)
        diskCachePolicy(CachePolicy.ENABLED)
        networkCachePolicy(CachePolicy.ENABLED)
        scale(Scale.FILL)
        scaleType = ImageView.ScaleType.CENTER_CROP
    }
}