package id.yuana.jokesbapack2.data.remote

import id.yuana.jokesbapack2.data.remote.response.GetTextAllResponse
import id.yuana.jokesbapack2.data.remote.response.GetTextRandomResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokesBapack2Api {

    companion object {
        const val BASE_URL = "https://jokes-bapack2-api.herokuapp.com/"
    }

    @GET("/v1/text")
    suspend fun getAll(): Response<GetTextAllResponse>

    @GET("/v1/text/random")
    suspend fun getRandom(): Response<GetTextRandomResponse>
}