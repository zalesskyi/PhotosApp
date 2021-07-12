package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.Photo
import com.zalesskyi.domain.repositories.MediaRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class RemoveFavoriteUseCase
@Inject
constructor(private val repository: MediaRepository) : UseCaseCoroutine<Photo, Unit>() {

    override suspend fun executeOnBackground(params: Photo) {
        repository.removeFavoritePhoto(params)
    }
}