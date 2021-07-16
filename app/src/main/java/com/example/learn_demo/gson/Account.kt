package com.example.learn_demo.gson

/**
 * @author vianhuang
 * @date 2021/7/14 6:16 下午
 */
class Account {
    var uid: String? = null
    var userName: String? = null
    var telNumber: String = "000000000"

    override fun toString(): String {
        return "Account(uid=$uid, userName=$userName, telNumber=$telNumber)"
    }
}

class Account2(val id: String) {
    var uid: String? = null
    var userName: String? = null
    var telNumber: String = "000000000"

    init {
        telNumber = "111111111"
    }

    override fun toString(): String {
        return "Account2(id='$id', uid=$uid, userName=$userName, telNumber='$telNumber')"
    }
}