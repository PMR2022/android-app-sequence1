package fr.ec.sequence1.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ec.sequence1.data.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(posts : List<Post>)

    @Query("SELECT * FROM POST")
    suspend fun getPosts() : List<Post>
}