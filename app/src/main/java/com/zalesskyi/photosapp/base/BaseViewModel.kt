package com.zalesskyi.photosapp.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.usecases.base.CompletionBlock
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import com.zalesskyi.photosapp.tools.SingleLiveEvent
import kotlinx.coroutines.launch

interface BaseViewModel {

    val progressLiveData: LiveData<ProgressEvent>

    val errorLiveData: LiveData<Throwable>
}

open class BaseViewModelImpl : ViewModel(), BaseViewModel {

    override val progressLiveData = SingleLiveEvent<ProgressEvent>()

    override val errorLiveData = SingleLiveEvent<Throwable>()

    fun <T, R, U : UseCaseCoroutine<T, R>>U.launch(param: T, block: CompletionBlock<R>) {
        viewModelScope.launch { execute(param, block) }
    }

    @CallSuper
    protected open fun showProgress() {
        progressLiveData.value = ProgressEvent.Show
    }

    @CallSuper
    protected open fun hideProgress() {
        progressLiveData.value = ProgressEvent.Hide
    }
}