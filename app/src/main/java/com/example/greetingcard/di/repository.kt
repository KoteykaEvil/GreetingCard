package com.example.greetingcard.di

import com.example.greetingcard.data.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val repository: Module = module {
    single {
        Repository(androidContext())
    }
}
