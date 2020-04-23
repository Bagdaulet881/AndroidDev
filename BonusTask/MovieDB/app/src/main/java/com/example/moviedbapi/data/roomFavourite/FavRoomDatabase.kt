//package com.example.moviedbapi.data.roomFavourite
//
//import com.example.moviedbapi.data.models.MovieData
//
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
////@Database(entities = [MovieData::class], version = 1, exportSchema = false)
//abstract class FavRoomDatabase: RoomDatabase() {
//    abstract fun favDao(): FavouritesDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: FavRoomDatabase? = null
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): FavRoomDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FavRoomDatabase::class.java,
//                    "movie_table"
//                )
//                    .fallbackToDestructiveMigration()
//                    .addCallback(MovieDatabaseCallback(scope))
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//
//    }
//
//    private class MovieDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                }
//            }
//        }
//
//    }
//}