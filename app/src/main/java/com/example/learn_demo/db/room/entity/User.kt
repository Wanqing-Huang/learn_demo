package com.example.learn_demo.db.room.entity

import android.graphics.Bitmap
import androidx.room.*

/**
 * @author vianhuang
 * @date 2021/10/11 6:23 下午
 *
 * 定义Table
 */

//定义复合主键
//@Entity(primaryKeys = ["firstName", "lastName"])

////自定义表名，默认是类名
//@Entity(tableName = "users")

@Entity
data class User(
    @PrimaryKey
    val uid: Int,

    //自定义列名，不定义的默认使用字段名
    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "age")
    val age: Int? = null,

    //Embedded用于标记嵌套对象，这个对象的所有字段会被加入User表中
    @Embedded
    var address: Address? = null
) {
    //不需要保留进db的字段
    @Ignore
    var picture: Bitmap? = null
}

data class Address(
    @ColumnInfo(name = "street")
    val street: String? = null,
    @ColumnInfo(name = "state")
    val state: String? = null,
    @ColumnInfo(name = "city")
    val city: String? = null
)


open class Book {
    var cover: Bitmap? = null
}

//不需要保留进db的父类字段使用'ignoredColumns'
@Entity(ignoredColumns = ["cover"])
data class TextBook(
    @PrimaryKey val id: Int,
    val hasVpn: Boolean
) : Book()




