package com.zalesskyi.domain.repositories

import com.zalesskyi.domain.models.Photo

interface MediaRepository {

    suspend fun getPhotos(limit: Int, offset: Int): List<Photo>

    suspend fun getFavorites(): List<Photo>

    suspend fun addFavoritePhoto(photo: Photo)

    suspend fun removeFavoritePhoto(photo: Photo)

    suspend fun checkFavorite(url: String): Boolean
}