package id.yuana.jokesbapack2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.yuana.jokesbapack2.data.local.JokesBapack2Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DB_NAME = "jokes.bapack2.db"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): JokesBapack2Database {
        return Room.databaseBuilder(
            appContext,
            JokesBapack2Database::class.java,
            DB_NAME
        ).build()
    }
}