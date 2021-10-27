package com.example.learn_demo.db.greendao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.learn_demo.AppUtils

/**
 * @author vianhuang
 * @date 2021/10/25 5:54 下午
 */
object DatabaseHelper {
    private const val TAG = "greendao"

    val daoSession by lazy {
        val devOpenHelper = GreenDaoUpgradeHelper(AppUtils.context, "greendao_db", null)
        DaoMaster(devOpenHelper.writableDb).newSession()
    }

    class GreenDaoUpgradeHelper(
        context: Context,
        name: String,
        factory: SQLiteDatabase.CursorFactory?
    ) :
        DaoMaster.OpenHelper(context, name, factory) {
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            Log.d(TAG, "upgrade greendao db from $oldVersion to $newVersion.")

            val migrations = if (oldVersion == 1 && newVersion == 2) {
                listOf(MIGRATIONS.MIGRATION_1_2)
            } else null

            migrations?.forEach { it.migrate(db) }
        }

        object MIGRATIONS {
            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SQLiteDatabase?) {
                    database?.execSQL("ALTER TABLE 'USER' ADD 'address' String")
                }
            }
        }

        abstract class Migration(val startVersion: Int, val endVersion: Int) {
            abstract fun migrate(database: SQLiteDatabase?)
        }
    }
}



