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



    val gson = Gson()

    suspend fun getPosts() :  List<PostResponse> = withContext(Dispatchers.Default)  {
        val json = makeCall()

        gson.fromJson(json,PostsResponse::class.java).posts
    }


     private suspend fun makeCall(): String? =  withContext(Dispatchers.IO) {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        try {
            urlConnection = URL(POST_API_URL).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            reader = urlConnection.inputStream?.bufferedReader()
             reader?.readText()

        } finally {
            urlConnection?.disconnect()
            reader?.close()
        }
    }
}