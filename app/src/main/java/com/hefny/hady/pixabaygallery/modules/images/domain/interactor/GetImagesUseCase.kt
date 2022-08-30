package com.hefny.hady.pixabaygallery.modules.images.domain.interactor

import androidx.paging.PagingData
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.repository.ImagesRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

data class GetImagesUseCase @Inject constructor(private val imagesRepository: ImagesRepository) {
    operator fun invoke(query: String): Flowable<PagingData<ImageEntity>> {
        return imagesRepository.getImages(query)
    }
}