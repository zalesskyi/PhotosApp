package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.Page
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.domain.repositories.MediaRepository
import com.zalesskyi.domain.usecases.base.UseCaseCoroutine
import javax.inject.Inject

class GetPhotosUrlUseCase
@Inject
constructor(private val repository: MediaRepository) : UseCaseCoroutine<Page, List<Photo>>() {

    override suspend fun executeOnBackground(params: Page): List<Photo> =
        params.run { repository.getPhotos(limit, offset) }
            .map { photo ->
                photo.copy(isFavorite = repository.checkFavorite(photo.url))
            }
}