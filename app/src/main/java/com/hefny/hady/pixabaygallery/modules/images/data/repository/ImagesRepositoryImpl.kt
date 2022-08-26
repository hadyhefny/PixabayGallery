package com.hefny.hady.pixabaygallery.modules.images.data.repository

import com.hefny.hady.pixabaygallery.modules.images.data.mapper.toEntity
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.ImagesRemoteDs
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.repository.ImagesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

data class ImagesRepositoryImpl @Inject constructor(private val imagesRemoteDs: ImagesRemoteDs) :
    ImagesRepository {
    override fun getImages(query: String): Single<HitsEntity> {
        return imagesRemoteDs.getImages(query).map {
            it.toEntity()
        }
    }
}