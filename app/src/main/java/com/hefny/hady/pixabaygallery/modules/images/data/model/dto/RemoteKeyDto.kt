package com.hefny.hady.pixabaygallery.modules.images.data.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hefny.hady.pixabaygallery.core.data.source.local.REMOTE_KEY_TABLE

@Entity(tableName = REMOTE_KEY_TABLE)
data class RemoteKeyDto(
    @PrimaryKey val query: String,
    val page: Int
)