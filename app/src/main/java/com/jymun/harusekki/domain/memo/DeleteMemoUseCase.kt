package com.jymun.harusekki.domain.memo

import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.data.repository.memo.MemoRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteMemoUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val memoRepository: MemoRepository
) {

    suspend operator fun invoke(
        memo: Memo
    ) = withContext(dispatcherProvider.default) {

        memoRepository.deleteMemo(
            memo.toEntity()
        )
    }
}