package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.data.source.recipe.RecipeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsRecipeRemoteDataSource(
        recipeRemoteDataSource: RecipeRemoteDataSource
    ): RecipeDataSource.Remote
}