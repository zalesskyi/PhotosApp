package com.zalesskyi.data.repositories

import com.zalesskyi.data.mappers.toDomain
import com.zalesskyi.data.mappers.toEntity
import com.zalesskyi.data.network.api.MediaApi
import com.zalesskyi.data.storage.dao.FavoritesDao
import com.zalesskyi.domain.models.Photo
import com.zalesskyi.domain.repositories.MediaRepository
import javax.inject.Inject

class MediaRepositoryImpl
@Inject
constructor(private val api: MediaApi, private val dao: FavoritesDao) : MediaRepository {

    override suspend fun getPhotos(limit: Int, offset: Int): List<Photo> =
        api.getPhotos(limit, offset).images.toDomain()

    override suspend fun getFavorites(): List<Photo> =
        dao.getAll().map { it.toDomain() }

    override suspend fun addFavoritePhoto(photo: Photo) {
        dao.add(photo.toEntity())
    }

    override suspend fun removeFavoritePhoto(photo: Photo) {
        dao.remove(photo.toEntity())
    }

    override suspend fun checkFavorite(url: String): Boolean =
        dao.isFavorite(url) > 0
}