package com.jymun.harusekki.di.domain.recipe

import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.domain.recipe.*
import com.jymun.harusekki.domain.recipe.favorite.DeleteFavoriteRecipeUseCase
import com.jymun.harusekki.domain.recipe.favorite.InsertFavoriteRecipeUseCase
import com.jymun.harusekki.domain.recipe.favorite.LoadFavoriteRecipeUseCase
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RecipeUseCaseModule {

    @Provides
    fun provideLoadAllRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        LoadAllRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideSearchRecipeByTitleUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        SearchRecipeByTitleUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideSearchRecipeByIngredientUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        SearchRecipeByIngredientUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideLoadRecipeDetailUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        LoadRecipeDetailUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideLoadLatestReadRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        LoadLatestReadRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideInsertLatestReadRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        InsertLatestReadRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideDeleteOldestReadRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        DeleteOldestReadRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideLoadFavoriteRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        LoadFavoriteRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideInsertFavoriteRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        InsertFavoriteRecipeUseCase(
            dispatcherProvider, recipeRepository
        )

    @Provides
    fun provideDeleteFavoriteRecipeUseCase(
        dispatcherProvider: DispatcherProvider,
        recipeRepository: RecipeRepository
    ) =
        DeleteFavoriteRecipeUseCase(
            dispatcherProvider, recipeRepository
        )
}