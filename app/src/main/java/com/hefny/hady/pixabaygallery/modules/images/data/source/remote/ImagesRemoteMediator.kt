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
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.RemoteKeyDto
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ExperimentalPagingApi
class ImagesRemoteMediator @Inject constructor(
    private val query: String,
    private val pixabayDatabase: PixabayDatabase,
    private val pixabayService: PixabayService
) : RxRemoteMediator<Int, ImageDto>() {
    private val imagesDao = pixabayDatabase.pixabayDao()
    private val remoteKeyDao = pixabayDatabase.remoteKeyDao()
    private val TAG = "AppDebug"

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, ImageDto>
    ): Single<MediatorResult> {
        Log.d(TAG, "loadSingle: loadType: $loadType, state: $state")
        return Single.just(loadType)
            .subscribeOn(Schedulers.io())
            .map {
                when (it) {
                    REFRESH -> 1
                    PREPEND -> INVALID_PAGE
                    APPEND -> {
                        val lastItem = state.lastItemOrNull()
                        if (lastItem == null) {
                            INVALID_PAGE
                        } else {
                            remoteKeyDao.getRemoteKey(query).blockingGet().page
                        }
                    }
                    else -> {
                        INVALID_PAGE
                    }
                }
            }.flatMap { page ->
                if (page == INVALID_PAGE) {
                    Single.just(MediatorResult.Success(endOfPaginationReached = true))
                } else {
                    pixabayService.searchImages(query, page)
                        .flatMap {
                            if (it.imagesResponse.isNotEmpty())
                                remoteKeyDao.insert(RemoteKeyDto(query, page + 1))
                                    .andThen(Single.just(it))
                            else
                                Single.just(it)
                        }
                        .flatMap {
                            if (it.imagesResponse.isEmpty()) {
                                Single.just(MediatorResult.Success(endOfPaginationReached = true) as MediatorResult)
                            } else {
                                imagesDao.insertAll(it.toEntity().images.toDto(query))
                                    .andThen(
                                        Single.just(
                                            MediatorResult.Success(
                                                endOfPaginationReached = false
                                            ) as MediatorResult
                                        )
                                    )
                            }
                        }
                        .doOnError {
                            Log.d(TAG, "loadSingle: $it")
                            MediatorResult.Error(it)
                        }
                }
            }
    }

    companion object {
        private const val INVALID_PAGE = -1
    }
}