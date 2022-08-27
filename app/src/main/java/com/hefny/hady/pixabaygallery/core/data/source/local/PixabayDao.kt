package com.hefny.hady.pixabaygallery.core.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefny.hady.pixabaygallery.modules.images.data.source.local.model.ImageDto
import io.reactivex.rxjava3.core.Completable

@Dao
interface PixabayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImageDto>): Completable

    @Query("SELECT * FROM image WHERE tags LIKE '%' || :query || '%'")
    fun getImages(query: String): PagingSource<Int, ImageDto>
}