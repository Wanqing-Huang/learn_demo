package com.example.learn_demo.db.room.dao

import androidx.room.*
import com.example.learn_demo.db.room.entity.PlaylistSongCrossRef

/**
 * @author vianhuang
 * @date 2021/10/21 5:23 下午
 */
@Dao
interface PlaylistSongCrossRefDao {
    //=================== insert =====================//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: PlaylistSongCrossRef)

    //=================== update =====================//


    //=================== delete =====================//


    //=================== query =====================//

}