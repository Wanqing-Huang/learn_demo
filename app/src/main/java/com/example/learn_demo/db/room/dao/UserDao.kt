package com.example.learn_demo.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learn_demo.db.room.entity.User
import com.example.learn_demo.db.room.entity.UserWithPlaylists

/**
 * @author vianhuang
 * @date 2021/10/11 6:25 下午
 */
@Dao
interface UserDao {
    //=================== insert =====================//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBothUsers(user1: User, user2: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersAndFriends(user: User, friends: List<User>)

    //=================== update =====================//
    @Update
    suspend fun updateUsers(vararg users: User)

    //=================== delete =====================//
    @Delete
    suspend fun deleteUsers(vararg users: User)

    //=================== query =====================//
    @Query("SELECT * FROM user")
    suspend fun queryAllUsers(): Array<User>

    //返回列的子集
    @Query("SELECT name, age FROM user")
    suspend fun queryFullName(): List<NameTuple>

    data class NameTuple(
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "age") val age: Int?
    )

    //当数据库更新时，Room会自动更新LiveData
    //只要数据库发生变化都会回调
    @Query("SELECT * FROM user WHERE age > :minAge")
    fun queryMatchedUsers(minAge: Int): LiveData<List<User>>

    //该方法需要 Room 运行两次查询，因此应向该方法添加 @Transaction 注释，以确保整个操作以原子方式执行
    @Transaction
    @Query("SELECT * FROM User")
    fun queryUsersAndPlaylists(): List<UserWithPlaylists>
}