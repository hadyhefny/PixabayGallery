package com.hefny.hady.pixabaygallery.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto

@Database(entities = [ImageDto::class], version = 1)
abstract class PixabayDatabase : RoomDatabase() {
    abstract fun pixabayDao(): PixabayDao
}

const val IMAGE_TABLE = "image"