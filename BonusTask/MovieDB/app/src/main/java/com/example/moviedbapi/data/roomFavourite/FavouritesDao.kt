package com.example.moviedbapi.data.roomFavourite

import com.example.moviedbapi.data.models.MovieData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedbapi.data.models.MovieResponseData

@Dao
interface FavouritesDao {
//
    @Query("SELECT * FROM movie_table")
    fun getFavMovies(accountId: Int, sessionId: String, page: Int): LiveData<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieData)

//    @Query("DELETE FROM movie_table")
//    suspend fun deleteAll()
//
//    @Query("SELECT * FROM movie_table WHERE id=:id")
//    fun getCinema(id: Int): LiveData<MovieData>
}