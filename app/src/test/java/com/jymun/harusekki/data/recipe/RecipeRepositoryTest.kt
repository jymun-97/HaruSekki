package com.jymun.harusekki.data.recipe

import com.google.common.truth.Truth.assertThat
import com.jymun.harusekki.data.cache.recipe.RecipeDataCache
import com.jymun.harusekki.data.cache.recipe.RecipeDataCacheImpl
import com.jymun.harusekki.data.entity.recipe.RecipeEntity
import com.jymun.harusekki.data.repository.recipe.RecipeRepository
import com.jymun.harusekki.data.repository.recipe.RecipeRepositoryImpl
import com.jymun.harusekki.data.source.recipe.RecipeDataSource
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
class RecipeRepositoryTest {

    private val fakeRecipeList = (0 until 100).map {
        RecipeEntity(
            id = it.toLong(),
            title = "Title $it",
            category = "밥/죽/떡",
            summary = "Summary $it",
            imgList = emptyList(),
            hits = 0,
            likes = 0,
            ingredientList = emptyList(),
            cookingStepList = emptyList()
        )
    }
    private val testDispatcherProvider = TestDispatcherProvider()

    private lateinit var recipeDataSource: RecipeDataSource
    private lateinit var recipeDataCache: RecipeDataCache
    private lateinit var recipeRepository: RecipeRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcherProvider.testDispatcher)

        recipeDataSource = FakeRecipeRemoteDataSource(fakeRecipeList)
        recipeDataCache = RecipeDataCacheImpl(testDispatcherProvider)
        recipeRepository =
            RecipeRepositoryImpl(testDispatcherProvider, recipeDataSource, recipeDataCache)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadAll_with_emptyCache() = runTest {
        assertThat(
            recipeRepository.loadAll("latest", false)
        ).isEqualTo(fakeRecipeList)
    }

    @Test
    fun searchByTitle_with_emptyCache() = runTest {
        val keyword = "3"
        assertThat(
            recipeRepository.searchByTitle(keyword, "latest", false)
                .all { it.title.contains(keyword) }
        ).isTrue()
    }

    @Test
    fun searchByCategory_with_emptyCache() = runTest {
        val category = "밥/죽/떡"
        assertThat(
            recipeRepository.searchByCategory(category, "latest", false)
                .all { it.category == category }
        ).isTrue()
    }

    @Test
    fun loadAll_with_updatedCache() = runTest {
        recipeRepository.loadAll("latest", false)

        assertThat(
            recipeDataSource.loadAll("latest")
        ).isEqualTo(recipeDataCache.loadAll("latest"))

        assertThat(
            recipeRepository.loadAll("latest", false)
        ).isEqualTo(recipeDataCache.loadAll("latest"))
    }

    @Test
    fun searchByTitle_with_updatedCache() = runTest {
        recipeRepository.loadAll("latest", false)
        val keyword = "3"

        assertThat(
            recipeDataSource.searchByTitle(keyword, "latest")
        ).isEqualTo(recipeDataCache.searchByTitle(keyword, "latest"))

        assertThat(
            recipeRepository.searchByTitle(keyword, "latest", false)
        ).isEqualTo(recipeDataCache.searchByTitle(keyword, "latest"))
    }

    @Test
    fun searchByCategory_with_updatedCache() = runTest {
        recipeRepository.loadAll("latest", false)
        val category = "밥/죽/떡"

        assertThat(
            recipeDataSource.searchByCategory(category, "latest")
        ).isEqualTo(recipeDataCache.searchByCategory(category, "latest"))

        assertThat(
            recipeRepository.searchByCategory(category, "latest", false)
        ).isEqualTo(recipeDataCache.searchByCategory(category, "latest"))
    }

    @Test
    fun loadDetail_with_emptyCache() = runTest {
        val id = 5L
        assertThat(
            recipeRepository.loadDetail(id, false)
        ).isEqualTo(fakeRecipeList.find { it.id == 5L })
    }

    @Test
    fun loadAll_with_refreshing() = runTest {
        recipeRepository.loadAll("latest", false)
        val newFakeRecipeList = fakeRecipeList + RecipeEntity(
            id = 100,
            title = "This is New Entity Title",
            category = "밥/죽/찌개",
            summary = "This is New Entity Summary",
            imgList = emptyList(),
            hits = 100,
            likes = 100,
            ingredientList = emptyList(),
            cookingStepList = emptyList()
        )
        (recipeDataSource as FakeRecipeRemoteDataSource).updateRecipeList(newFakeRecipeList)

        assertThat(
            recipeDataCache.loadAll("latest")
        ).isNotEqualTo(recipeDataSource.loadAll("latest"))

        assertThat(
            recipeRepository.loadAll("latest", true)
        ).isEqualTo(newFakeRecipeList)

        assertThat(
            recipeDataCache.loadAll("latest")
        ).isEqualTo(recipeDataSource.loadAll("latest"))
    }
}