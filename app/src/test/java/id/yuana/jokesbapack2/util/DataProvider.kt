package id.yuana.jokesbapack2.util

import com.google.gson.Gson
import id.yuana.jokesbapack2.data.remote.response.GetTextAllResponse

object DataProvider {

    private fun getFromResource(): GetTextAllResponse {
        val rawResponseBody =
            MockResponseFileReader("response_success.json").content
        val responseBody = Gson().fromJson(rawResponseBody, GetTextAllResponse::class.java)
        return responseBody
    }

    fun getDataFromApi(): List<String> = getFromResource().data

//    fun getDataFromDbEmpty(): Flow<List<CoinItemEntity>> = flowOf(listOf())

//    fun getDataModel(): List<CoinItemModel> =
//        getDataFromApi().map { it.mapToEntity().mapFromEntity() }
}