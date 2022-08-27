package com.hefny.hady.pixabaygallery.modules.images.domain.entity

data class ImageEntity(
    val id: Long,
    val tags: String,
    val previewUrl: String,
    val largeImageUrl: String,
    val userName: String,
    val noOfLikes: Long,
    val noOfDownloads: Long,
    val noOfComments: Long
)