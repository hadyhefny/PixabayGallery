package com.hefny.hady.pixabaygallery.core.di

import android.content.Context
import androidx.room.Room
import com.hefny.hady.pixabaygallery.core.data.source.local.PixabayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Volatile
    private var instance: PixabayDatabase? = null
    private const val DATABASE_NAME = "pixabay_db"

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): PixabayDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): PixabayDatabase {
        return Room.databaseBuilder(context, PixabayDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}