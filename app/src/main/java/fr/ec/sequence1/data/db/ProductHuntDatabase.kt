package fr.ec.sequence1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ec.sequence1.data.Post

@Database(entities = [Post::class],version = 1)
abstract class ProductHuntDatabase : RoomDatabase(){

    abstract fun postDao() : PostDao
}