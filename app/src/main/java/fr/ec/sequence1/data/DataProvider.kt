package fr.ec.sequence1.data

import com.google.gson.Gson
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object DataProvider {
    private val POST_API_URL =
        "https://api.producthunt.com/v1/posts?access_token=45f13bcd50dc73c343a53c55af0d150dabd1dac4d6b035a6d2265ed5e85e0e81"

    val gson = Gson()

    fun getPosts() :  List<PostResponse> {
        val json = makeCall()

        return gson.fromJson(json,PostsResponse::class.java).posts
    }


     private fun makeCall(): String? {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        try {
            urlConnection = URL(POST_API_URL).openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()

            reader = urlConnection.inputStream?.bufferedReader()
            return reader?.readText()

        } finally {
            urlConnection?.disconnect()
            reader?.close()
        }
    }
}