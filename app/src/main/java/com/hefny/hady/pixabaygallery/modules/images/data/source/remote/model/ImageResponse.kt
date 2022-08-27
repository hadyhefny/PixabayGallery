package com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("user")
    val userName: String,
    @SerializedName("likes")
    val noOfLikes: Long,
    @SerializedName("downloads")
    val noOfDownloads: Long,
    @SerializedName("comments")
    val noOfComments: Long
)