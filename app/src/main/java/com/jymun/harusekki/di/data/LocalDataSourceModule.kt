package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.source.ingredient.IngredientDateSource
import com.jymun.harusekki.data.source.ingredient.IngredientLocalDataSource
import com.jymun.harusekki.data.source.memo.MemoDataSource
import com.jymun.harusekki.data.source.memo.MemoLocalDataSource
import com.jymun.harusekki.data.source.menu.MenuDataSource
import com.jymun.harusekki.data.source.menu.MenuLocalDataSource
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

    @Binds
    @Singleton
    abstract fun bindsMenuLocalDataSource(
        menuLocalDataSource: MenuLocalDataSource
    ): MenuDataSource

    @Binds
    @Singleton
    abstract fun bindsIngredientLocalDataSource(
        ingredientLocalDateSource: IngredientLocalDataSource
    ): IngredientDateSource.Local

    @Binds
    @Singleton
    abstract fun bindsMemoLocalDataSource(
        memoLocalDataSource: MemoLocalDataSource
    ): MemoDataSource
}