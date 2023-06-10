package com.minamagid.thechallenge.data.local

import androidx.room.*
import com.minamagid.thechallenge.domain.model.homeResponses.Result
import com.minamagid.thechallenge.domain.model.homeResponses.ListStringConverter
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class NYTimesDB : RoomDatabase() {
    abstract val localRoomDao: LocalRoomDao
    companion object {
        const val DATABASE_NAME = "NYTimesDB"
    }
}