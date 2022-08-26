package com.hefny.hady.pixabaygallery.modules.images.presentation.model

import com.hefny.hady.pixabaygallery.modules.images.domain.entity.HitsEntity

data class ImagesState(
    val isLoading: Boolean = false,
    val data: HitsEntity? = null,
    val error: Throwable? = null
)