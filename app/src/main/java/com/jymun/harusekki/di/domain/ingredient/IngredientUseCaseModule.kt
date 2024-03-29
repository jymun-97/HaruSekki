package com.jymun.harusekki.di.domain.ingredient

import com.jymun.harusekki.data.repository.ingredient.IngredientRepository
import com.jymun.harusekki.domain.ingredient.*
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class IngredientUseCaseModule {

    @Provides
    fun provideSearchAllIngredientUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        SearchAllIngredientUseCase(dispatcherProvider, ingredientRepository)

    @Provides
    fun provideSearchIngredientByTitleUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        SearchIngredientByTitleUseCase(dispatcherProvider, ingredientRepository)

    @Provides
    fun provideSearchIngredientByCategoryUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        SearchIngredientByCategoryUseCase(dispatcherProvider, ingredientRepository)

    @Provides
    fun provideLoadIngredientsInRefrigeratorByCategoryUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        LoadIngredientsInRefrigeratorUseCase(dispatcherProvider, ingredientRepository)

    @Provides
    fun provideAddIngredientInRefrigeratorByCategoryUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        AddIngredientInRefrigeratorUseCase(dispatcherProvider, ingredientRepository)

    @Provides
    fun provideDeleteIngredientInRefrigeratorByCategoryUseCase(
        dispatcherProvider: DispatcherProvider,
        ingredientRepository: IngredientRepository
    ) =
        DeleteIngredientInRefrigeratorUseCase(dispatcherProvider, ingredientRepository)
}