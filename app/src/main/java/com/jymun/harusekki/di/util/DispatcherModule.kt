package com.jymun.harusekki.di.util

import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(provider: DispatcherProviderImpl): DispatcherProvider
}