package com.jymun.harusekki.di.util

import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import com.jymun.harusekki.util.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcherProvider(provider: DispatcherProviderImpl): DispatcherProvider
}