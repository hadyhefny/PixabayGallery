package com.hefny.hady.pixabaygallery.modules.images.data.mapper

import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity

fun ImageDto.toEntity() = ImageEntity(
    id,
    tags,
    previewUrl,
    largeImageUrl,
    userName,
    noOfLikes,
    noOfDownloads,
    noOfComments
)

fun List<ImageDto>.toEntity() = map { it.toEntity() }

fun ImageEntity.toDto() = ImageDto(
    id,
    tags,
    previewUrl,
    largeImageUrl,
    userName,
    noOfLikes,
    noOfDownloads,
    noOfComments
)

fun List<ImageEntity>.toDto() = map { it.toDto() }