package com.minamagid.thechallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minamagid.thechallenge.domain.model.homeResponses.Result

@Dao
interface LocalRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Result?)

    @Query("delete from RESULT where id =:idData")
    suspend fun deleteItemLocalRoom(idData: Long?)

    @Query("SELECT * FROM RESULT")
    suspend fun getItemsFromLocalRoom(): List<Result?>

    @Query("DELETE FROM RESULT")
    suspend fun clearTable()

}