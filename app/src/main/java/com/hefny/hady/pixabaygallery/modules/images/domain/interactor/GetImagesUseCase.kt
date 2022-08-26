package com.hefny.hady.pixabaygallery.modules.images.domain.interactor

import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.repository.ImagesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

data class GetImagesUseCase @Inject constructor(private val imagesRepository: ImagesRepository) {
    operator fun invoke(query: String): Single<HitsEntity> {
        return imagesRepository.getImages(query)
    }
}