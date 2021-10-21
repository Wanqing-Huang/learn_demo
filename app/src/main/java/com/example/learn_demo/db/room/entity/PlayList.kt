package com.example.learn_demo.db.room.entity

import androidx.room.*

/**
 * @author vianhuang
 * @date 2021/10/21 4:04 下午
 */
//一对多关系
@Entity
data class Playlist(
    @PrimaryKey val playlistId: Long,
    val userCreatorId: Long,
    val playlistName: String
)

data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userCreatorId"
    )
    val playlists: List<Playlist>
)

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(value = PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)