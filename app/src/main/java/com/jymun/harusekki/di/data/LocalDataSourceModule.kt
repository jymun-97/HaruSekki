package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.data.source.recipe.RecipeLocalDataSource
import com.jymun.harusekki.data.source.recipe.SearchKeywordDataSource
import com.jymun.harusekki.data.source.recipe.SearchKeywordLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsSearchKeywordLocalDataSource(
        searchKeywordLocalDataSource: SearchKeywordLocalDataSource
    ): SearchKeywordDataSource

    @Binds
    @Singleton
    abstract fun bindsRecipeLocalDataSource(
        recipeLocalDataSource: RecipeLocalDataSource
    ): RecipeDataSource.Local
}