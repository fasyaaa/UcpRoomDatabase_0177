package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.RsDatabase
import com.example.ucp2.repository.LocalRepositoryDkr
import com.example.ucp2.repository.LocalRepositoryJdw
import com.example.ucp2.repository.RepositoryDkr
import com.example.ucp2.repository.RepositoryJdw

interface InterfaceContainerApp{
    val repositoryDkr: RepositoryDkr
    val repositoryJdw: RepositoryJdw
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryDkr: RepositoryDkr by lazy {
        LocalRepositoryDkr(RsDatabase.getDatabase(context).dokterDao())
    }
    override val repositoryJdw: RepositoryJdw by lazy {
        LocalRepositoryJdw(RsDatabase.getDatabase(context).jadwalDao())
    }
}