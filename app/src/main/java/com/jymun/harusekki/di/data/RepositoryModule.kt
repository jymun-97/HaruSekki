package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.repository.SearchKeywordRepository
import com.jymun.harusekki.data.repository.SearchKeywordRepositoryImpl
import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.data.repository.ingredient.IngredientRepositoryImpl
import com.jymun.harusekki.data.repository.memo.MemoRepository
import com.jymun.harusekki.data.repository.memo.MemoRepositoryImpl
import com.jymun.harusekki.data.repository.menu.MenuRepository
import com.jymun.harusekki.data.repository.menu.MenuRepositoryImpl
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.data.repository.recipe.RecipeRepositoryImpl
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

    @Binds
    @Singleton
    abstract fun bindsMenuRepository(
        menuRepositoryImpl: MenuRepositoryImpl
    ): MenuRepository

    @Binds
    @Singleton
    abstract fun bindsIngredientRepository(
        ingredientRepositoryImpl: IngredientRepositoryImpl
    ): IngredientRepository

    @Binds
    @Singleton
    abstract fun bindsMemoRepository(
        memoRepositoryImpl: MemoRepositoryImpl
    ): MemoRepository
}