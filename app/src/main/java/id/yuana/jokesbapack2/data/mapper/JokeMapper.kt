package id.yuana.jokesbapack2.data.mapper

import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import id.yuana.jokesbapack2.data.remote.response.GetTextAllResponse

fun GetTextAllResponse.transformToJokeEntities(): List<JokeEntity> =
    this.data.map { JokeEntity(content = it) }