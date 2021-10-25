package com.example.learn_demo.db.room.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * @author vianhuang
 * @date 2021/10/22 5:15 下午
 *
 * TypeConverter可以实现一个复杂类型对象和一个基础类型字段的来回转换
 */
open class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}