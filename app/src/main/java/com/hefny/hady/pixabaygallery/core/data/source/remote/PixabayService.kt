package com.hefny.hady.pixabaygallery.core.data.source.remote

import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.model.MainResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("api/")
    fun getImages(
        @Query("q") name: String
    ): Single<MainResponse>
}