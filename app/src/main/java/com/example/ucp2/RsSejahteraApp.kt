package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependenciesinjection.ContainerApp

class RsSejahteraApp : Application(){
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}