package id.yuana.jokesbapack2.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetTextRandomResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val msg: String,
    @SerializedName("data") val data: String
)
