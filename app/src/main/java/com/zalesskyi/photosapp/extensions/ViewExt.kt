package com.zalesskyi.photosapp.extensions

import android.view.View

fun View.toggleSelected() {
    isSelected = !isSelected
}