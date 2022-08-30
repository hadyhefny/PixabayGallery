package com.hefny.hady.pixabaygallery.modules.images.data.mapper

import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model.ImageResponse
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

fun ImageResponse.toEntity(query: String) = ImageDto(
    id = id,
    query = query,
    tags = tags,
    previewUrl = previewURL,
    largeImageUrl = largeImageURL,
    userName = userName,
    noOfLikes = noOfLikes,
    noOfDownloads = noOfDownloads,
    noOfComments = noOfComments
)

fun List<ImageResponse>.toEntity(query: String) = map { it.toEntity(query) }