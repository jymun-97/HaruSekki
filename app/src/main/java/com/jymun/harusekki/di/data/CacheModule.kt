package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.cache.recipe.RecipeDataCache
import com.jymun.harusekki.data.cache.recipe.RecipeDataCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun bindsRecipeDataCache(
        recipeDataCacheImpl: RecipeDataCacheImpl
    ): RecipeDataCache
}