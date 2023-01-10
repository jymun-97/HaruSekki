package com.jymun.harusekki.di.data

import android.content.Context
import androidx.room.Room
import com.jymun.harusekki.data.db.recipe.RecipeDatabase
import com.jymun.harusekki.data.db.recipe.SearchKeywordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSearchKeywordDatabase(
        @ApplicationContext context: Context
    ): SearchKeywordDatabase = Room.databaseBuilder(
        context.applicationContext,
        SearchKeywordDatabase::class.java,
        "search_keyword"
    ).build()

    @Provides
    @Singleton
    fun provideRecipeDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase = Room.databaseBuilder(
        context.applicationContext,
        RecipeDatabase::class.java,
        "recipe"
    ).build()
}