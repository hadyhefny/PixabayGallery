package com.hefny.hady.pixabaygallery.modules.images.data.mapper

import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model.ImageResponse
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model.MainResponse
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity

fun ImageResponse.toEntity() =
    ImageEntity(
        id,
        tags,
        previewURL,
        largeImageURL,
        userName,
        noOfLikes,
        noOfDownloads,
        noOfComments
    )

fun MainResponse.toEntity() = HitsEntity(imagesResponse.map { it.toEntity() })