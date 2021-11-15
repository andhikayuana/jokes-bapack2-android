package id.yuana.jokesbapack2.data.local.dao

import androidx.room.*
import id.yuana.jokesbapack2.data.local.entity.JokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM jokes")
    fun getAll(): Flow<List<JokeEntity>>

    @Query("SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Flow<JokeEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jokeEntity: JokeEntity)

    @Query("SELECT COUNT(id) FROM jokes")
    fun count(): Flow<Int>
}