package com.jymun.harusekki.domain

import com.google.common.truth.Truth.assertThat
import com.jymun.harusekki.data.cache.recipe.RecipeDataCache
import com.jymun.harusekki.data.cache.recipe.RecipeDataCacheImpl
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.recipe.FakeRecipeRemoteDataSource
import com.jymun.harusekki.data.repository.RecipeRepository
import com.jymun.harusekki.data.repository.RecipeRepositoryImpl
import com.jymun.harusekki.data.source.recipe.RecipeDataSource
import com.jymun.harusekki.domain.recipe.LoadAllRecipeUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByCategoryUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByIngredientUseCase
import com.jymun.harusekki.domain.recipe.SearchRecipeByTitleUseCase
import com.jymun.harusekki.util.TestDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchRecipeUseCasesTest {

    private val fakeRecipeList = (0 until 100).map {
        RecipeEntity(
            id = it.toLong(),
            title = "Title $it",
            category = "밥/죽/떡",
            summary = "Summary $it",
            imgList = emptyList(),
            hits = 0,
            likes = 0,
            ingredientList = null,
            cookingStepList = null
        )
    }
    private val testDispatcherProvider = TestDispatcherProvider()

    private lateinit var recipeDataSource: RecipeDataSource
    private lateinit var recipeDataCache: RecipeDataCache
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var loadAllRecipeUseCase: LoadAllRecipeUseCase
    private lateinit var searchRecipeByCategoryUseCase: SearchRecipeByCategoryUseCase
    private lateinit var searchRecipeByTitleUseCase: SearchRecipeByTitleUseCase
    private lateinit var searchRecipeByIngredientUseCase: SearchRecipeByIngredientUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcherProvider.testDispatcher)

        recipeDataSource = FakeRecipeRemoteDataSource(fakeRecipeList)
        recipeDataCache = RecipeDataCacheImpl(testDispatcherProvider)
        recipeRepository =
            RecipeRepositoryImpl(testDispatcherProvider, recipeDataSource, recipeDataCache)

        loadAllRecipeUseCase = LoadAllRecipeUseCase(testDispatcherProvider, recipeRepository)
        searchRecipeByTitleUseCase =
            SearchRecipeByTitleUseCase(testDispatcherProvider, recipeRepository)
        searchRecipeByCategoryUseCase =
            SearchRecipeByCategoryUseCase(testDispatcherProvider, recipeRepository)
        searchRecipeByIngredientUseCase =
            SearchRecipeByIngredientUseCase(testDispatcherProvider, recipeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadAll() = runTest {
        assertThat(
            loadAllRecipeUseCase().map { it.toEntity() }
        ).isEqualTo(fakeRecipeList)
    }

    @Test
    fun searchByTitle() = runTest {
        val keyword = "3"
        assertThat(
            searchRecipeByTitleUseCase(keyword).map { it.toEntity() }
        ).isEqualTo(
            fakeRecipeList.filter { it.title.contains(keyword) }
        )
    }

    @Test
    fun searchByCategory() = runTest {
        val category = "밥/죽/떡"
        assertThat(
            searchRecipeByCategoryUseCase(category).map { it.toEntity() }
        ).isEqualTo(
            fakeRecipeList.filter { it.category == category }
        )
    }
}