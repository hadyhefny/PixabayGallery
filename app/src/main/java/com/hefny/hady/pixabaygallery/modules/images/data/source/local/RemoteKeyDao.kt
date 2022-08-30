package com.hefny.hady.pixabaygallery.modules.images.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hefny.hady.pixabaygallery.modules.images.data.model.dto.RemoteKeyDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(remoteKeyDto: RemoteKeyDto): Completable

    @Query("SELECT * FROM remote_key WHERE `query` = :searchQuery")
    fun getRemoteKey(searchQuery: String): Single<RemoteKeyDto>
}