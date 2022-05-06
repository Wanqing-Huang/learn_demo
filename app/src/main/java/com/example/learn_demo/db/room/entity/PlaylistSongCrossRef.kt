package com.example.learn_demo.db.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author vianhuang
 * @date 2021/10/21 5:09 下午
 */
@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    @ColumnInfo(index = true)
    val songId: Long
)

