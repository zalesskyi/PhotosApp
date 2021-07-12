package com.zalesskyi.photosapp.base

import android.os.Bundle
import android.widget.PopupWindow
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity(layoutRes: Int) : AppCompatActivity(layoutRes) {

    protected open var onFragmentChanged: ((Fragment) -> Unit)? = null

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        onFragmentChanged = null
    }

    protected fun showSnackMessage(@StringRes message: Int) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }
}