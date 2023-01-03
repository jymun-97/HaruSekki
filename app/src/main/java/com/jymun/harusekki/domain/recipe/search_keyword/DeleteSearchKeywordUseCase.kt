package com.jymun.harusekki.domain.recipe.search_keyword

import com.jymun.harusekki.data.model.recipe.SearchKeyword
import com.jymun.harusekki.data.repository.SearchKeywordRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteSearchKeywordUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val searchKeywordRepository: SearchKeywordRepository
) {

    suspend operator fun invoke(searchKeyword: SearchKeyword) = withContext(dispatcherProvider.io) {
        searchKeywordRepository.deleteKeyword(
            searchKeyword.toEntity()
        )
    }
}