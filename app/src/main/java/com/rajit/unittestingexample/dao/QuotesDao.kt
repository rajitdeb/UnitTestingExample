package com.rajit.unittestingexample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rajit.unittestingexample.entity.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {

    @Insert
    suspend fun insertQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote")
    suspend fun deleteAllQuotes()

    @Query("SELECT * FROM quote")
    fun getAllQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM quote")
    fun getAllQuotesViaFlow(): Flow<List<Quote>>

    @Query("SELECT * FROM quote WHERE id = :quoteId")
    suspend fun getQuoteById(quoteId: Int): Quote

}