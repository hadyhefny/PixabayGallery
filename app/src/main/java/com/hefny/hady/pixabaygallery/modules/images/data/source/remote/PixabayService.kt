package com.hefny.hady.pixabaygallery.modules.images.data.source.remote

import com.hefny.hady.pixabaygallery.modules.images.data.model.api.MainResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("api/")
    fun searchImages(
        @Query("q") name: String,
        @Query("page") page: Int,
        @Query("order") order: String = "latest"
    ): Single<MainResponse>
}