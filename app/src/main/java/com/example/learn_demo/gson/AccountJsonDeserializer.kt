package com.example.learn_demo.gson

import com.google.gson.*
import java.lang.reflect.Type

/**
 * @author vianhuang
 * @date 2021/7/14 6:17 下午
 */
class AccountJsonDeserializer : JsonDeserializer<Account>, JsonSerializer<Account> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Account {
        return Account().apply {
            json?.asJsonObject?.let {
                uid = it.get("uid").asString
                userName = it.get("userName").asString
                telNumber = it.get("telNumber").asString
            }
        }
    }

    override fun serialize(
        src: Account?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonObject().apply {
            src?.let {
                addProperty("uid", it.uid)
                addProperty("userName", it.userName)
                addProperty("telNumber", it.telNumber)
            }
        }
    }
}