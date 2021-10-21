package com.example.learn_demo.db

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.learn_demo.R
import com.example.learn_demo.db.room.DatabaseHelper
import com.example.learn_demo.db.room.entity.*
import kotlinx.android.synthetic.main.activity_db_test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2021/10/14 11:23 上午
 */
class DBTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "DBTestActivity"
    }

    private val users = listOf(
        User(1, address = Address("street1", "state1", "city1")),
        User(2, age = 14, address = Address("street2", "state2", "city2")),
        User(3, age = 15, address = Address("street3", "state3", "city3")),
        User(4, age = 16, name = "name4"),
        User(5, age = 17, address = Address("street5", "state5", "city5")),
        User(6, age = 18, name = "name6")
    )

    private val playlist = listOf(
        Playlist(1, 1, "playlist1"),
        Playlist(2, 2, "playlist2"),
        Playlist(3, 6, "playlist3"),
        Playlist(4, 1, "playlist4")
    )

    private val songList = listOf(
        Song(1, "song1", "artist1"),
        Song(2, "song2", "artist2"),
        Song(3, "song3", "artist3"),
        Song(4, "song4", "artist4"),
        Song(5, "song5", "artist5"),
        Song(6, "song6", "artist6"),
    )

    private val relationList = listOf(
        PlaylistSongCrossRef(1, 1),
        PlaylistSongCrossRef(1, 2),
        PlaylistSongCrossRef(2, 1),
        PlaylistSongCrossRef(2, 3),
        PlaylistSongCrossRef(2, 4),
        PlaylistSongCrossRef(3, 1),
        PlaylistSongCrossRef(3, 5),
        PlaylistSongCrossRef(3, 6),
        PlaylistSongCrossRef(4, 6)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_test)

        initData()

        btn_insert_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                DatabaseHelper.db.userDao().insertUsers(users[0])
                DatabaseHelper.db.userDao().insertBothUsers(users[1], users[2])
                DatabaseHelper.db.userDao()
                    .insertUsersAndFriends(users[3], listOf(users[4], users[5]))
            }
        }

        btn_delete_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val uid = Random.nextInt(0, 6)
                DatabaseHelper.db.userDao().deleteUsers(users[uid])
                Log.d(TAG, "delete user[$uid] from db.")
            }
        }

        btn_query_test.setOnClickListener {
            queryLiveDataTest()
        }


        btn_update_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val uid = Random.nextInt(0, 6)
                val oldUser = users[uid]
                val newUser = User(
                    uid = oldUser.uid,
                    age = Random.nextInt(1, 100),
                    address = oldUser.address,
                    name = oldUser.name
                )

                DatabaseHelper.db.userDao().updateUsers(newUser)
                Log.d(TAG, "update user[$uid] to $newUser.")
            }
        }

        //一对一与一对多的处理方式相同
        btn_one_to_more_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val users = DatabaseHelper.db.userDao().queryUsersAndPlaylists()
                var result = ""
                users.forEach { result += (it.toString() + "\n") }
                Log.d(TAG, "query user and playlists. $result")

                withContext(Dispatchers.Main) {
                    txt_result.text = result
                }
            }
        }

        btn_more_to_more_test.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                var result = ""

                val items = DatabaseHelper.db.playListDao().getPlaylistsWithSongs()
                items.forEach { result += (it.toString() + "\n") }

                result += "\n"

                val songs = DatabaseHelper.db.songDao().getSongsWithPlaylists()
                songs.forEach { result += (it.toString() + "\n") }

                Log.d(TAG, "more to more relation test. $result")

                withContext(Dispatchers.Main) {
                    txt_result.text = result
                }
            }
        }
    }

    private fun initData() {
        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseHelper.db.playListDao().insert(*playlist.toTypedArray())
            DatabaseHelper.db.songDao().insert(*songList.toTypedArray())
            DatabaseHelper.db.playlistSongCrossRefDao().insert(*relationList.toTypedArray())
        }
    }

    private fun queryTest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val users = DatabaseHelper.db.userDao().queryAllUsers()

            var result = ""
            users.forEach { result += (it.toString() + "\n") }

            withContext(Dispatchers.Main) {
                txt_result.text = result
            }
        }
    }

    private fun queryLiveDataTest() {
        lifecycleScope.launch(Dispatchers.IO) {
            val usersLiveData = DatabaseHelper.db.userDao().queryMatchedUsers(50)
            withContext(Dispatchers.Main) {
                usersLiveData.observe(this@DBTestActivity) { users ->
                    var result = ""
                    users.forEach { result += (it.toString() + "\n") }
                    Log.d(TAG, "livaData changed. $result")

                    lifecycleScope.launch(Dispatchers.Main) {
                        txt_result.text = result
                    }
                }
            }
        }
    }
}