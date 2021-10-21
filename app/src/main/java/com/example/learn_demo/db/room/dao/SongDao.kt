package com.example.learn_demo.db.room.dao

import androidx.room.*
import com.example.learn_demo.db.room.entity.Song
import com.example.learn_demo.db.room.entity.SongWithPlaylists

/**
 * @author vianhuang
 * @date 2021/10/21 5:15 下午
 */

@Dao
interface SongDao {
    //=================== insert =====================//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg song: Song)
    //=================== update =====================//


    //=================== delete =====================//


    //=================== query =====================//
    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>
}