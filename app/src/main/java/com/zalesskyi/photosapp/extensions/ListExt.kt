package com.zalesskyi.photosapp.extensions

import android.os.Parcelable
import com.github.nitrico.lastadapter.StableId
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

const val DEFAULT_SKELETON_LIST_SIZE = 40

fun List<Parcelable>.skeleton(range: Int = DEFAULT_SKELETON_LIST_SIZE) = toMutableList().apply {
    IntRange(0, range.dec()).map { AppSkeletonItem() }.toList<Parcelable>().let {
        clear()
        addAll(it)
    }
}

@Parcelize
data class AppSkeletonItem(val id: String = ""): Parcelable, StableId {
    @IgnoredOnParcel
    override val stableId = id.hashCode().toLong()
}