package com.dannndi.moview.core.di

import androidx.room.Room
import com.dannndi.moview.core.data.datasource.local.MovieLocalDatabase
import com.dannndi.moview.core.data.datasource.remote.MovieServices
import com.dannndi.moview.core.data.repository.MovieDaoRepository
import com.dannndi.moview.core.data.repository.MovieServicesRepository
import com.dannndi.moview.core.domain.repository.IMovieDaoRepository
import com.dannndi.moview.core.domain.repository.IMovieServicesRepository
import com.dannndi.moview.core.domain.usecase.MovieDaoInteractor
import com.dannndi.moview.core.domain.usecase.MovieDaoUseCase
import com.dannndi.moview.core.domain.usecase.MovieServicesInteractor
import com.dannndi.moview.core.domain.usecase.MovieServicesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(MovieServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MovieServices::class.java)
    }
}

val localDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieLocalDatabase::class.java,
            "db_movie"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<MovieLocalDatabase>().movieDao() }
}

val repositoryModule = module {
    single<IMovieServicesRepository> {
        MovieServicesRepository(get())
    }
    single<MovieServicesUseCase> {
        MovieServicesInteractor(get())
    }

    single<IMovieDaoRepository> {
        MovieDaoRepository(get())
    }
    single<MovieDaoUseCase> {
        MovieDaoInteractor(get())
    }
}

