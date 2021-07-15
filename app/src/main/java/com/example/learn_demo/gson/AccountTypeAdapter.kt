package com.example.learn_demo.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

/**
 * @author vianhuang
 * @date 2021/7/14 6:15 下午
 */
class AccountTypeAdapter : TypeAdapter<Account>() {
    override fun write(out: JsonWriter?, value: Account?) {
        out?.let { writer ->
            value?.let { account ->
                writer.beginObject().name("uid").value(account.uid)
                    .name("userName").value(account.userName)
                    .name("telNumber").value(account.telNumber)
                    .endObject()
            }
        }
    }

    override fun read(input: JsonReader?): Account {
        return Account().apply {
            input?.let {
                input.beginObject()
                while (input.hasNext()) {
                    when (input.nextName()) {
                        "uid" -> uid = input.nextString()
                        "userName" -> userName = input.nextString()
                        "telNumber" -> telNumber = input.nextString()
                    }
                }
                input.endObject()
            }
        }
    }
}