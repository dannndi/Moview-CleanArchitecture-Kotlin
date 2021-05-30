package com.dannndi.moview.di

import android.app.Application
import com.dannndi.moview.core.di.localDatabaseModule
import com.dannndi.moview.core.di.networkModule
import com.dannndi.moview.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    localDatabaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}