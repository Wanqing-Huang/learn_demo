package com.example.learn_demo.db.greendao.converter

import com.example.learn_demo.db.room.entity.Address
import com.google.gson.Gson
import org.greenrobot.greendao.converter.PropertyConverter

/**
 * @author vianhuang
 * @date 2021/10/27 4:50 下午
 */
class AddressConverter : PropertyConverter<Address, String> {
    private val gson = Gson()
    override fun convertToEntityProperty(value: String?): Address? {
        return value?.let {
            gson.fromJson(it, Address::class.java)
        }
    }

    override fun convertToDatabaseValue(value: Address?): String? {
        return value?.let {
            gson.toJson(it)
        }
    }
}