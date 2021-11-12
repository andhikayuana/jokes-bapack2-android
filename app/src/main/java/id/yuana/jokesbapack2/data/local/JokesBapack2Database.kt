package id.yuana.jokesbapack2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.yuana.jokesbapack2.data.local.dao.JokeDao
import id.yuana.jokesbapack2.data.local.entity.JokeEntity

@Database(
    version = 1,
    entities = [JokeEntity::class]
)
abstract class JokesBapack2Database : RoomDatabase() {

    abstract fun jokeDao(): JokeDao
}