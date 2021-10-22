package com.example.learn_demo.db.room.entity

import androidx.room.DatabaseView

/**
 * @author vianhuang
 * @date 2021/10/22 3:02 下午
 *
 * 创建视图，其实就是创建了一张中间表，真实存在的表，所以添加视图需要增加数据库版本号。
 * 源数据表数据变化时，会自动更新视图表数据
 *
 * 创建视图与创建表的区别：
 *      1. 视图的数据来源于其他表，不需要手动添加或者维护，表的数据需要手动添加或者维护
 *      2. 视图只允许query，表允许增删改查
 */

@DatabaseView(
    "SELECT uid, name, age, playlistId, playlistName FROM user INNER JOIN playlist ON user.uid = playlist.userCreatorId"
)
data class UserPlayList(
    val uid: Int,
    val name: String? = null,
    val age: Int? = null,
    val playlistId: Long,
    val playlistName: String
)