package com.hefny.hady.pixabaygallery.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.RemoteKeyDto

@Database(entities = [ImageDto::class, RemoteKeyDto::class], version = 1, exportSchema = false)
abstract class PixabayDatabase : RoomDatabase() {
    abstract fun pixabayDao(): PixabayDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}

const val IMAGE_TABLE = "image"
const val REMOTE_KEY_TABLE = "remote_key"