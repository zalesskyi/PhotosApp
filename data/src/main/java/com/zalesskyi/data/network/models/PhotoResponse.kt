package com.zalesskyi.data.network.models

import com.google.gson.annotations.SerializedName

data class PhotoResponse(@SerializedName("id")
                         val id: String,
                         @SerializedName("member_id")
                         val memberId: String)