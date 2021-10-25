package com.example.learn_demo.db.greendao

import com.example.learn_demo.AppUtils
import com.example.learn_demo.db.greendao.bean.DaoMaster

/**
 * @author vianhuang
 * @date 2021/10/25 5:54 下午
 */
object DatabaseHelper {
    val daoSession by lazy {
        val devOpenHelper = DaoMaster.DevOpenHelper(AppUtils.context, "greendao_db", null)
        DaoMaster(devOpenHelper.writableDb).newSession()
    }
}