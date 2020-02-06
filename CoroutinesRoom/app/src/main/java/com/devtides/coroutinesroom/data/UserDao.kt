package com.devtides.coroutinesroom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devtides.coroutinesroom.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUser(username: String): User

    @Query("DELETE FROM user WHERE id = :id")
    fun deleteUser(id: Long)
}