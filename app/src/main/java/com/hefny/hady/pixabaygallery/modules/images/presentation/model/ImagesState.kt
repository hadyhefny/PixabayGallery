package com.hefny.hady.pixabaygallery.modules.images.presentation.model

import androidx.paging.PagingData
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity

data class ImagesState(
    val isLoading: Boolean = false,
    val data: PagingData<ImageEntity>? = null,
    val error: Throwable? = null
)