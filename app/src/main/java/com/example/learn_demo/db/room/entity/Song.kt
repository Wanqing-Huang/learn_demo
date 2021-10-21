package com.example.learn_demo.db.room.entity

import androidx.room.*

/**
 * @author vianhuang
 * @date 2021/10/21 5:07 下午
 */

@Entity
data class Song(
    @PrimaryKey val songId: Long,
    val songName: String,
    val artist: String
)

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playlistId",
        associateBy = Junction(value = PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>
)


