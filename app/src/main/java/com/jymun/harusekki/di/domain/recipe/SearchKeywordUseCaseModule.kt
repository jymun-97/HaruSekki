package com.jymun.harusekki.di.domain.recipe

import com.jymun.harusekki.data.repository.SearchKeywordRepository
import com.jymun.harusekki.domain.recipe.search_keyword.AddSearchKeywordUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.DeleteAllSearchKeywordsUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.DeleteSearchKeywordUseCase
import com.jymun.harusekki.domain.recipe.search_keyword.LoadAllSearchKeywordsUseCase
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchKeywordUseCaseModule {

    @Provides
    fun provideAddSearchKeywordUseCase(
        dispatcherProvider: DispatcherProvider,
        searchKeywordRepository: SearchKeywordRepository
    ): AddSearchKeywordUseCase {

        return AddSearchKeywordUseCase(dispatcherProvider, searchKeywordRepository)
    }

    @Provides
    fun provideDeleteSearchKeywordUseCase(
        dispatcherProvider: DispatcherProvider,
        searchKeywordRepository: SearchKeywordRepository
    ): DeleteSearchKeywordUseCase {

        return DeleteSearchKeywordUseCase(dispatcherProvider, searchKeywordRepository)
    }

    @Provides
    fun provideLoadAllSearchKeywordsUseCase(
        dispatcherProvider: DispatcherProvider,
        searchKeywordRepository: SearchKeywordRepository
    ): LoadAllSearchKeywordsUseCase {

        return LoadAllSearchKeywordsUseCase(dispatcherProvider, searchKeywordRepository)
    }

    @Provides
    fun provideDeleteAllSearchKeywordsUseCase(
        dispatcherProvider: DispatcherProvider,
        searchKeywordRepository: SearchKeywordRepository
    ): DeleteAllSearchKeywordsUseCase {

        return DeleteAllSearchKeywordsUseCase(dispatcherProvider, searchKeywordRepository)
    }
}