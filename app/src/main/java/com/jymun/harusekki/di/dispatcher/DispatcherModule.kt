package com.jymun.harusekki.di.dispatcher

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