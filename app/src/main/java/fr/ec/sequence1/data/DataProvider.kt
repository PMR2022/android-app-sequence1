package fr.ec.sequence1.data

import android.app.Application
import androidx.room.Room
import fr.ec.sequence1.data.api.PostResponse
import fr.ec.sequence1.data.api.ProductHuntService
import fr.ec.sequence1.data.db.ProductHuntDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class DataProvider(app: Application) {
    private val POST_API_URL =
        "https://api.producthunt.com/v1/posts?access_token=45f13bcd50dc73c343a53c55af0d150dabd1dac4d6b035a6d2265ed5e85e0e81"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.producthunt.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productHuntService = retrofit.create<ProductHuntService>()


    private val database = Room.databaseBuilder(
        app,
        ProductHuntDatabase::class.java, "database-name"
    ).build()

    private val postDao = database.postDao()

    suspend fun getPosts(): List<Post>  {

       return try {
            val posts = productHuntService.getPosts().posts.toPost()
            postDao.saveOrUpdate(posts)
            posts
        } catch (exception: Exception) {
            postDao.getPosts()
        }
    }

    private fun List<PostResponse>.toPost(): List<Post> {
        return this.map { postResponse ->
            Post(
                id = postResponse.id,
                title = postResponse.name,
                subtitle = postResponse.tagline,
                imageUrl = postResponse.thumbnail.imageUrl
            )
        }
    }

}