package fr.ec.sequence1.data

import android.content.Context
import fr.ec.sequence1.data.api.PostResponse
import fr.ec.sequence1.data.api.ProductHuntService
import fr.ec.sequence1.data.database.PostDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DataProvider(context: Context) {
    private val POST_API_URL =
        "https://api.producthunt.com/v1/posts?access_token=45f13bcd50dc73c343a53c55af0d150dabd1dac4d6b035a6d2265ed5e85e0e81"

   private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.producthunt.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productHuntService = retrofit.create<ProductHuntService>()

    private val postDao = PostDao(context)

    suspend fun getPosts(): List<Post> = withContext(Dispatchers.IO) {

         try {
            val posts = productHuntService.getPosts().posts.toPost()
            posts.forEach { post ->
                postDao.save(post)
            }
            posts
        } catch (exception: Exception) {
            postDao.retrievePosts()
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