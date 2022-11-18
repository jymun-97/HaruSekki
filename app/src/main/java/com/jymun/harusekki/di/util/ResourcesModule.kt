package com.jymun.harusekki.di.util

import com.jymun.harusekki.util.resources.ResourcesProvider
import com.jymun.harusekki.util.resources.ResourcesProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Binds
    abstract fun bindResourcesProvider(provider: ResourcesProviderImpl): ResourcesProvider
}