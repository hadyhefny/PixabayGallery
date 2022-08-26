package com.hefny.hady.pixabaygallery.modules.images.data.source.remote

import com.hefny.hady.pixabaygallery.core.data.source.remote.PixabayService
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model.MainResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

data class ImagesRemoteDs @Inject constructor(private val pixabayService: PixabayService) {
    fun getImages(query: String): Single<MainResponse> {
        return pixabayService.getImages(query)
    }
}