package com.zalesskyi.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zalesskyi.data.storage.models.FavoritePhotoEntity

@Dao
interface FavoritesDao {

    @Insert
    fun add(photo: FavoritePhotoEntity)

    @Delete
    fun remove(photo: FavoritePhotoEntity)

    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoritePhotoEntity>

    @Query("SELECT count(*) FROM favorites WHERE photoUrl=:url")
    suspend fun isFavorite(url: String): Int
}