package com.example.learn_demo.db.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learn_demo.db.room.dao.PlayListDao
import com.example.learn_demo.db.room.dao.PlaylistSongCrossRefDao
import com.example.learn_demo.db.room.dao.SongDao
import com.example.learn_demo.db.room.dao.UserDao
import com.example.learn_demo.db.room.entity.Playlist
import com.example.learn_demo.db.room.entity.PlaylistSongCrossRef
import com.example.learn_demo.db.room.entity.Song
import com.example.learn_demo.db.room.entity.User

/**
 * @author vianhuang
 * @date 2021/10/11 6:26 下午
 *
 * 定义DB
 */
@Database(
    entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun playListDao(): PlayListDao
    abstract fun songDao(): SongDao
    abstract fun playlistSongCrossRefDao(): PlaylistSongCrossRefDao
}