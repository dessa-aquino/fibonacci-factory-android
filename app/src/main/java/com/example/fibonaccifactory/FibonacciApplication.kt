package com.example.fibonaccifactory

import android.app.Application
import com.example.fibonaccifactory.di.appModule
import com.example.fibonaccifactory.di.domainModule
import com.example.fibonaccifactory.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FibonacciApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@FibonacciApplication)
            modules(
                appModule,
                domainModule,
                presentationModule
            )
        }
    }
} 