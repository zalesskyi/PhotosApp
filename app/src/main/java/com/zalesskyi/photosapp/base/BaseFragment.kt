package com.zalesskyi.photosapp.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import javax.inject.Inject

abstract class BaseFragment<out VM : BaseViewModel>(layoutRes: Int) : Fragment(layoutRes) {

    companion object {

        const val NO_TITLE = -1
        private const val DEBOUNCE_TIME_MS = 300L
    }

    protected abstract val viewModel: VM

    protected open val titleRes: Int = NO_TITLE
    protected open val skeletonViews = listOf<View?>()
    protected open val contentViews = listOf<View?>()
    protected open val softInputType: Int? get() = activity?.window?.attributes?.softInputMode

    protected abstract fun observeViewModel()

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        subscribe()
    }

    protected open fun showProgress() {
        skeletonVisible(true)
        contentVisible(false)
    }

    protected open fun hideProgress() {
        skeletonVisible(false)
        contentVisible(true)
    }

    protected open fun contentVisible(visible: Boolean) =
        contentViews.forEach { it?.isVisible = visible }

    protected open fun skeletonVisible(visible: Boolean) =
        skeletonViews.forEach { it?.isVisible = visible }

    private fun subscribe() {
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            when (it) {
                ProgressEvent.Show -> showProgress()
                ProgressEvent.Hide -> hideProgress()
            }
        }
    }

    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) =
        observe(viewLifecycleOwner) {
            observer(it)
        }
}