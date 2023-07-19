package com.demo.jetpack.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    fun insertAll(vararg students: Student)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>
}