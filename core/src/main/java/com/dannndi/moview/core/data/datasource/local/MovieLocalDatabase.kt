package com.dannndi.moview.core.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dannndi.moview.core.data.datasource.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieLocalDatabase : RoomDatabase(){
    abstract fun movieDao() : MovieDao
}