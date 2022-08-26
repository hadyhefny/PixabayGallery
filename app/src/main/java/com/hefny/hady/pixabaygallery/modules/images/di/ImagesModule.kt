package com.hefny.hady.pixabaygallery.modules.images.di

import com.hefny.hady.pixabaygallery.modules.images.data.repository.ImagesRepositoryImpl
import com.hefny.hady.pixabaygallery.modules.images.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class ImagesModule {
    @Binds
    @ViewModelScoped
    abstract fun bindImagesRepository(impl: ImagesRepositoryImpl): ImagesRepository
}