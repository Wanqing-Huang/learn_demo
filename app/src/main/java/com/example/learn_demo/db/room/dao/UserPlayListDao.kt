package com.example.learn_demo.db.room.dao

import androidx.room.*
import com.example.learn_demo.db.room.entity.UserPlayList

/**
 * @author vianhuang
 * @date 2021/10/22 3:11 下午
 */

//对视图只能执行query， insert，update，delete都不允许
@Dao
interface UserPlayListDao {
    //=================== insert =====================//

    //=================== update =====================//


    //=================== delete =====================//


    //=================== query =====================//
    @Query("SELECT * FROM userplaylist")
    suspend fun queryAll(): List<UserPlayList>
}