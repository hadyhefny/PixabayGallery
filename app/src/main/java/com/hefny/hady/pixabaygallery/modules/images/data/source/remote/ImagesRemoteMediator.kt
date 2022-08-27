package com.hefny.hady.pixabaygallery.modules.images.data.source.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.hefny.hady.pixabaygallery.core.data.source.local.PixabayDatabase
import com.hefny.hady.pixabaygallery.core.data.source.remote.PixabayService
import com.hefny.hady.pixabaygallery.modules.images.data.mapper.toDto
import com.hefny.hady.pixabaygallery.modules.images.data.mapper.toEntity
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ExperimentalPagingApi
class ImagesRemoteMediator @Inject constructor(
    private val query: String,
    private val pixabayDatabase: PixabayDatabase,
    private val pixabayService: PixabayService
) : RxRemoteMediator<Int, ImageDto>() {
    private val TAG: String = "AppDebug"
    private val imagesDao = pixabayDatabase.pixabayDao()
    private var page: Int = 1

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, ImageDto>
    ): Single<MediatorResult> {
        Log.d(TAG, "loadSingle: loadType: $loadType, state: $state")
        page = when (loadType) {
            REFRESH -> 1
            PREPEND -> return Single.just(MediatorResult.Success(endOfPaginationReached = true))
            APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    page += 1
                }
                page
            }
        }
        return pixabayService.searchImages(query, page)
            .subscribeOn(Schedulers.io())
            .map {
                imagesDao.insertAll(it.toEntity().images.toDto())
                MediatorResult.Success(endOfPaginationReached = it.imagesResponse.isEmpty()) as MediatorResult
            }
            .onErrorResumeNext {
                Single.just(MediatorResult.Error(it))
            }
            .doOnSubscribe {
                Log.d(TAG, "loadSingle: doOnSubscribe")
            }
    }
}