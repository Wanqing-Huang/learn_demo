package com.example.learn_demo.db.room.db

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.learn_demo.AppUtils
import com.example.learn_demo.db.room.converter.Converters
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
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun build(): AppDatabase {
            return Room.databaseBuilder(
                AppUtils.context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                .addMigrations(MIGRATIONS.MIGRATION_1_2, MIGRATIONS.MIGRATION_2_3)
                .build()
        }
    }

    //======================= 定义dao =====================//
    abstract fun userDao(): UserDao
    abstract fun playListDao(): PlayListDao
    abstract fun songDao(): SongDao
    abstract fun playlistSongCrossRefDao(): PlaylistSongCrossRefDao
    abstract fun userPlayListDao(): UserPlayListDao

    //======================= 定义migration =====================//
    object MIGRATIONS {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `Song` (`songId` INTEGER NOT NULL, `songName` TEXT NOT NULL, `artist` TEXT NOT NULL, PRIMARY KEY(`songId`))")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE `User` ADD COLUMN ${User::birthday.name} INTEGER DEFAULT 0"
                )
            }
        }
    }
}