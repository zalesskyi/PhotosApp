package com.zalesskyi.photosapp.base
sealed class ProgressEvent {

    object Show : ProgressEvent()

    object Hide : ProgressEvent()

    object LoadMore : ProgressEvent()

    val inProgress get()  = this is Show
}