package com.zalesskyi.data.mappers

import com.zalesskyi.data.network.models.PhotoResponse
import com.zalesskyi.domain.models.Photo

fun PhotoResponse.toDomain() = Photo(String.format(Photo.URL_TEMPLATE, memberId, id), false)

fun List<PhotoResponse>.toDomain() = map { it.toDomain() }