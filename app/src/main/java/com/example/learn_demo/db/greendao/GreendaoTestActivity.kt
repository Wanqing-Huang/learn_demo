package com.example.learn_demo.db.greendao

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learn_demo.R
import com.example.learn_demo.db.greendao.bean.User
import com.example.learn_demo.db.room.RoomTestActivity
import com.example.learn_demo.db.room.entity.Address
import kotlinx.android.synthetic.main.activity_db_test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2021/10/14 11:23 上午
 */
class GreendaoTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "GreendaoTestActivity"
    }

    private val users = listOf(
        User().apply {
            uid = 1
            address = Address("street1", "state1", "city1")
            birthday = Date(2021, 10, 12)
        },
        User().apply {
            uid = 2
            age = 14
            address = Address("street2", "state2", "city2")
        },
        User().apply {
            uid = 3
            age = 15
            address = Address("street3", "state3", "city3")
            birthday = Date(2021, 10, 14)
        },
        User().apply {
            uid = 4
            age = 16
            name = "name4"
        },
        User().apply {
            uid = 5
            age = 17
            address = Address("street5", "state5", "city5")
        },
        User().apply {
            uid = 6
            age = 18
            name = "name6"
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_test)

        btn_insert_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                DatabaseHelper.daoSession.userDao.insertOrReplaceInTx(*users.toTypedArray())
            }
        }

        btn_delete_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val uid = Random.nextInt(0, 6)
                val users = DatabaseHelper.daoSession.userDao.delete(users[uid])
                Log.d(TAG, "delete user[$uid] from db.")
            }
        }

        btn_query_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val users = DatabaseHelper.daoSession.userDao.loadAll()

                var result = ""
                users.forEach { result += (it.toString() + "\n") }
                Log.d(RoomTestActivity.TAG, "test greendao query. $result")

                withContext(Dispatchers.Main) {
                    txt_result.text = result
                }
            }
        }


        btn_update_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val uid = Random.nextInt(0, 6)
                val oldUser = users[uid]
                val newUser = User(
                    oldUser.uid,
                    oldUser.name,
                    Random.nextInt(1, 100),
                    oldUser.address,
                    oldUser.birthday
                ).apply {
                    address = oldUser.address
                    name = oldUser.name
                }
                DatabaseHelper.daoSession.userDao.update(newUser)
                Log.d(TAG, "update user[$uid] to $newUser.")
            }
        }

        //一对一与一对多的处理方式相同
        btn_one_to_more_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

            }
        }

        btn_more_to_more_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

            }
        }
    }
}