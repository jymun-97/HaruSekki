package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.repository.RecipeRepository
import com.jymun.harusekki.data.repository.RecipeRepositoryImpl
import com.jymun.harusekki.data.repository.SearchKeywordRepository
import com.jymun.harusekki.data.repository.SearchKeywordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    @Singleton
    abstract fun bindsSearchKeywordRepository(
        searchKeywordRepositoryImpl: SearchKeywordRepositoryImpl
    ): SearchKeywordRepository
}