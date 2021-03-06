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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(MovieServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(MovieServices::class.java)
    }
}

val localDatabaseModule = module {
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moview".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieLocalDatabase::class.java,
            "db_movie"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
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

