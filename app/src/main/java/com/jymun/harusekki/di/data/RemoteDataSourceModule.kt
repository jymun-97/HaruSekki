package com.jymun.harusekki.di.data

import com.jymun.harusekki.data.source.ingredient.IngredientDateSource
import com.jymun.harusekki.data.source.ingredient.IngredientRemoteDataSource
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

    @Binds
    @Singleton
    abstract fun bindsIngredientRemoteDataSource(
        ingredientRemoteDataSource: IngredientRemoteDataSource
    ): IngredientDateSource.Remote
}