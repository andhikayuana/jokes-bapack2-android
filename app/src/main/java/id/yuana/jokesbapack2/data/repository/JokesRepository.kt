package id.yuana.jokesbapack2.data.repository

import id.yuana.jokesbapack2.data.local.JokesBapack2Database
import id.yuana.jokesbapack2.data.mapper.transformToJokeEntities
import id.yuana.jokesbapack2.data.remote.JokesBapack2Api
import id.yuana.jokesbapack2.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
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
        saveFetchResult = { result ->
            if (result.isSuccess) {
                db.clearAllTables()
                result.getOrNull()
                    ?.transformToJokeEntities()
                    ?.forEach { jokeEntity ->
                        db.jokeDao().insert(jokeEntity)
                    }
            } else {
                //todo: error handling
            }
        }
    ).flowOn(Dispatchers.IO)

    fun hasLocalData() = db.jokeDao().count().flowOn(Dispatchers.IO)

    fun getRandom() = db.jokeDao().getRandom().flowOn(Dispatchers.IO)

}