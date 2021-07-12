package com.zalesskyi.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritePhotoEntity(@PrimaryKey val photoUrl: String)