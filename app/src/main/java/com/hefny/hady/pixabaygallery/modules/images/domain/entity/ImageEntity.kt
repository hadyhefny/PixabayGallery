package com.hefny.hady.pixabaygallery.modules.images.domain.entity

data class ImageEntity(
    val id: Long,
    val tags: String,
    val previewURL: String,
    val largeImageURL: String,
    val userName: String,
    val noOfLikes: Long,
    val noOfDownloads: Long,
    val noOfComments: String
)