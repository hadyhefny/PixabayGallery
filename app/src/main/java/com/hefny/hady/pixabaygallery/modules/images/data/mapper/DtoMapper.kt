package com.hefny.hady.pixabaygallery.modules.images.data.mapper

import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
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

fun ImageEntity.toDto(query: String) = ImageDto(
    id = id,
    query = query,
    tags = tags,
    previewUrl = previewUrl,
    largeImageUrl = largeImageUrl,
    userName = userName,
    noOfLikes = noOfLikes,
    noOfDownloads = noOfDownloads,
    noOfComments = noOfComments
)

fun List<ImageEntity>.toDto(query: String) = map { it.toDto(query) }