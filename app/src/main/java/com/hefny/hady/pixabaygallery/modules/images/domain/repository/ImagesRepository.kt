package com.hefny.hady.pixabaygallery.modules.images.domain.repository

import androidx.paging.PagingData
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import io.reactivex.rxjava3.core.Flowable

interface ImagesRepository {
    fun getImages(query: String): Flowable<PagingData<ImageEntity>>
}