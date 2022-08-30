package com.hefny.hady.pixabaygallery.modules.images.data.repository

import androidx.paging.*
import androidx.paging.rxjava3.flowable
import com.hefny.hady.pixabaygallery.core.data.source.local.PixabayDatabase
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.PixabayService
import com.hefny.hady.pixabaygallery.modules.images.data.mapper.toEntity
import com.hefny.hady.pixabaygallery.modules.images.data.source.remote.ImagesRemoteMediator
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import com.hefny.hady.pixabaygallery.modules.images.domain.repository.ImagesRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ExperimentalPagingApi
class ImagesRepositoryImpl
@Inject constructor(
    private val pixabayDatabase: PixabayDatabase,
    private val pixabayService: PixabayService
) :
    ImagesRepository {
    private val imagesDao = pixabayDatabase.pixabayDao()
    override fun getImages(query: String): Flowable<PagingData<ImageEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ImagesRemoteMediator(query, pixabayDatabase, pixabayService),
            pagingSourceFactory = {
                imagesDao.pagingSource(query)
            }
        ).flowable.map { pagingData ->
            pagingData.map { imageDto ->
                imageDto.toEntity()
            }
        }
    }
}