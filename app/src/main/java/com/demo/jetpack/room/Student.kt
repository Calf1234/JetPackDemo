package com.demo.jetpack.room

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

open class User {
    var picture: Bitmap? = null
}

@Entity(tableName = "student", ignoredColumns = ["picture"])
data class Student(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val hasVpn: Boolean
) : User()