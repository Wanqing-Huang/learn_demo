package com.example.learn_demo.db.room.dao

import androidx.room.*
import com.example.learn_demo.db.room.entity.Playlist
import com.example.learn_demo.db.room.entity.PlaylistWithSongs
import com.example.learn_demo.db.room.entity.SongWithPlaylists

/**
 * @author vianhuang
 * @date 2021/10/11 6:25 下午
 */
@Dao
interface PlayListDao {
    //=================== insert =====================//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg playlist: Playlist)
    //=================== update =====================//


    //=================== delete =====================//


    //=================== query =====================//
    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>
}