package com.hefny.hady.pixabaygallery.modules.images.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.hefny.hady.pixabaygallery.core.data.source.local.PixabayDatabase
import com.hefny.hady.pixabaygallery.core.data.source.remote.PixabayService
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
    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, ImageDto>
    ): Single<MediatorResult> {
        return Single.just(loadType)
            .map {
                when (it) {
                    REFRESH -> 1
                    PREPEND -> INVALID_PAGE
                    APPEND -> remoteKeyDao.getRemoteKey(query).blockingGet().page
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
                                imagesDao.insertAll(it.imagesResponse.toEntity(query))
                                    .andThen(
                                        Single.just(
                                            MediatorResult.Success(
                                                endOfPaginationReached = false
                                            ) as MediatorResult
                                        )
                                    )
                            }
                        }
                        .onErrorReturn {
                            MediatorResult.Error(it)
                        }
                }
            }.subscribeOn(Schedulers.io())
    }

    companion object {
        private const val INVALID_PAGE = -1
    }
}