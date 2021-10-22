package com.example.learn_demo.db.room

import com.example.learn_demo.db.room.db.AppDatabase

/**
 * @author vianhuang
 * @date 2021/10/11 6:27 下午
 */
object DatabaseHelper {
    val db: AppDatabase = AppDatabase.build()
}