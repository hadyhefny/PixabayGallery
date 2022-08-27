package com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("total")
    val total: Long,
    @SerializedName("totalHits")
    val totalHits: Long,
    @SerializedName("hits")
    val imagesResponse: List<ImageResponse>
)