package com.zalesskyi.data.mappers

import com.zalesskyi.data.storage.models.FavoritePhotoEntity
import com.zalesskyi.domain.models.Photo

fun FavoritePhotoEntity.toDomain() = Photo(photoUrl, true)

fun Photo.toEntity() = FavoritePhotoEntity(url)