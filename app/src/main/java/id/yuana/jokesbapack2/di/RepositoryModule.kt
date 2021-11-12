package id.yuana.jokesbapack2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.yuana.jokesbapack2.data.local.JokesBapack2Database
import id.yuana.jokesbapack2.data.remote.JokesBapack2Api
import id.yuana.jokesbapack2.data.repository.JokesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideJokesRepository(
        db: JokesBapack2Database,
        api: JokesBapack2Api
    ) = JokesRepository(
        db = db,
        api = api
    )
}