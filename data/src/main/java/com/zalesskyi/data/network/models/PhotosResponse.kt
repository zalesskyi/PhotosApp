package com.zalesskyi.data.network.models

import com.google.gson.annotations.SerializedName

data class PhotosResponse(@SerializedName("images")
                          val images: List<PhotoResponse>)