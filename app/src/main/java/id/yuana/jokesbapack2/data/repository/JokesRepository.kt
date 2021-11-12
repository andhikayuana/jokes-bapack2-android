package id.yuana.jokesbapack2.data.repository

import id.yuana.jokesbapack2.data.local.JokesBapack2Database
import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import id.yuana.jokesbapack2.data.remote.JokesBapack2Api
import id.yuana.jokesbapack2.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val db: JokesBapack2Database,
    private val api: JokesBapack2Api
) {

    fun getAll() = networkBoundResource(
        query = {
            db.jokeDao().getAll()
        },
        fetch = {
            delay(500)
            api.getAll()
        },
        saveFetchResult = {
            it.body()?.let {
                it.data.map {
                    JokeEntity(content = it)
                }.forEach {
                    db.jokeDao().insert(it)
                }
            }

        }
    )
}