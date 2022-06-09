package fr.ec.sequence1.data.api

import retrofit2.http.GET

interface ProductHuntService {

    @GET("posts?access_token=45f13bcd50dc73c343a53c55af0d150dabd1dac4d6b035a6d2265ed5e85e0e81")
    suspend fun getPosts() : PostsResponse
}