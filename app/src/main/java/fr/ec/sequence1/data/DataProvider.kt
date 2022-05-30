package fr.ec.sequence1.data

import com.google.gson.Gson
import fr.ec.sequence1.data.api.ProductHuntService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object DataProvider {
    private val POST_API_URL =
        "https://api.producthunt.com/v1/posts?access_token=45f13bcd50dc73c343a53c55af0d150dabd1dac4d6b035a6d2265ed5e85e0e81"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.producthunt.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productHuntService = retrofit.create<ProductHuntService>()

    suspend fun getPosts() :  List<PostResponse> = productHuntService.getPosts().posts

}