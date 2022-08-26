package com.hefny.hady.pixabaygallery.modules.images.domain.repository

import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import io.reactivex.rxjava3.core.Single

interface ImagesRepository {
    fun getImages(query: String): Single<HitsEntity>
}