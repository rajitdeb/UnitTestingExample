package com.rajit.unittestingexample.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quote: String,
    val author: String
)
