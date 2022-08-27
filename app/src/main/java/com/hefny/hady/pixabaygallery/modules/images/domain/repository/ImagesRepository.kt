package com.hefny.hady.pixabaygallery.modules.images.domain.repository

import androidx.paging.PagingData
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface ImagesRepository {
    fun getImages(query: String): Flowable<PagingData<ImageEntity>>
}