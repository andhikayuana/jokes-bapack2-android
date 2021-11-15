package id.yuana.jokesbapack2.data.repository

import android.util.Log
import id.yuana.jokesbapack2.data.local.JokesBapack2Database
import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import id.yuana.jokesbapack2.data.remote.JokesBapack2Api
import id.yuana.jokesbapack2.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
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
        saveFetchResult = { res ->
            res.body()?.let {
                db.clearAllTables()
                it.data.map {
                    JokeEntity(content = it)
                }.forEach {
                    db.jokeDao().insert(it)
                }
            }

        }
    ).flowOn(Dispatchers.IO)

    fun hasLocalData() = db.jokeDao().count().flowOn(Dispatchers.IO)

    fun getRandom(): Flow<JokeEntity> {

        GlobalScope.launch {

            val x = db.jokeDao().getRandom().firstOrNull()
            Log.d("YUANA", x.toString())
        }

        return db.jokeDao().getRandom().flowOn(Dispatchers.IO)
    }
}