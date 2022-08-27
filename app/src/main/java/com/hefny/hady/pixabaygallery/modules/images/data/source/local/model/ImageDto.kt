package com.hefny.hady.pixabaygallery.modules.images.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefny.hady.pixabaygallery.core.data.source.local.IMAGE_TABLE

@Entity(tableName = IMAGE_TABLE)
data class ImageDto(
    @PrimaryKey val id: Long,
    val tags: String,
    @ColumnInfo(name = "preview_url") val previewUrl: String,
    @ColumnInfo(name = "large_image_url") val largeImageUrl: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "no_of_like") val noOfLikes: Long,
    @ColumnInfo(name = "no_of_downloads") val noOfDownloads: Long,
    @ColumnInfo(name = "no_of_comments") val noOfComments: Long
)