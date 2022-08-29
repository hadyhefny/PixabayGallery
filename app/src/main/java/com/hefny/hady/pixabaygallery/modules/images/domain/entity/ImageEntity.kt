package com.hefny.hady.pixabaygallery.modules.images.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageEntity(
    val id: Long,
    val tags: String,
    val previewUrl: String,
    val largeImageUrl: String,
    val userName: String,
    val noOfLikes: Long,
    val noOfDownloads: Long,
    val noOfComments: Long
) : Parcelable