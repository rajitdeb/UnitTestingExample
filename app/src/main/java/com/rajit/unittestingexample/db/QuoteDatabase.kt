package com.rajit.unittestingexample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rajit.unittestingexample.dao.QuotesDao
import com.rajit.unittestingexample.entity.Quote

@Database([Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuotesDao

}