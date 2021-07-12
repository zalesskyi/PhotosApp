package com.zalesskyi.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalesskyi.data.storage.dao.FavoritesDao
import com.zalesskyi.data.storage.models.FavoritePhotoEntity

@Database(entities = [FavoritePhotoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): FavoritesDao
}