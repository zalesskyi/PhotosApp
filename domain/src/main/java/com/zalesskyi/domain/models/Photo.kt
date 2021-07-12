package com.zalesskyi.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(val url: String,
                 var isFavorite: Boolean): Parcelable {

    companion object {

        const val URL_TEMPLATE = "https://photos.gurushots.com/unsafe/0x500/%s/3_%s.jpg"
    }
}