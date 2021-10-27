package com.example.learn_demo.db.greendao.converter

import org.greenrobot.greendao.converter.PropertyConverter
import java.util.*

/**
 * @author vianhuang
 * @date 2021/10/27 4:33 下午
 */
open class DateConverter : PropertyConverter<Date, Long> {
    override fun convertToEntityProperty(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    override fun convertToDatabaseValue(date: Date?): Long? {
        return date?.time
    }
}