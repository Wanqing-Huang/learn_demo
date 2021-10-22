package com.example.learn_demo.db.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learn_demo.db.room.dao.*
import com.example.learn_demo.db.room.entity.*

/**
 * @author vianhuang
 * @date 2021/10/11 6:26 下午
 *
 * 定义DB
 */
@Database(
    entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class],
    views = [UserPlayList::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun playListDao(): PlayListDao
    abstract fun songDao(): SongDao
    abstract fun playlistSongCrossRefDao(): PlaylistSongCrossRefDao
    abstract fun userPlayListDao(): UserPlayListDao
}