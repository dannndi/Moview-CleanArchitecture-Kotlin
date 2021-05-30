package com.dannndi.moview.core.data.datasource.local

import androidx.room.*
import com.dannndi.moview.core.data.datasource.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT count(*) from movie WHERE movie.id =:id")
    suspend fun checkFavoriteMovie(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteFavorite(movie: MovieEntity)

}