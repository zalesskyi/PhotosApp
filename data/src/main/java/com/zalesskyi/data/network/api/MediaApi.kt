package com.zalesskyi.data.network.api

import com.zalesskyi.data.network.models.PhotoResponse
import com.zalesskyi.data.network.models.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MediaApi {

    @GET("/app/get_photos")
    suspend fun getPhotos(@Query("limit") limit: Int = 10,
                          @Query("offset") offset: Int = 0): PhotosResponse
}