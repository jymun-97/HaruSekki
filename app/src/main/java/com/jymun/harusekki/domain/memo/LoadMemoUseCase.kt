package com.jymun.harusekki.domain.memo

import com.jymun.harusekki.data.model.ModelType
import com.jymun.harusekki.data.model.ingredient.Ingredient
import com.jymun.harusekki.data.model.ingredient.IngredientCategoryMapper
import com.jymun.harusekki.data.model.memo.Memo
import com.jymun.harusekki.data.repository.memo.MemoRepository
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadMemoUseCase @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val memoRepository: MemoRepository
) {

    suspend operator fun invoke() = withContext(dispatcherProvider.default) {

        return@withContext memoRepository.loadAll().map { entity ->
            Memo(
                id = entity.id,
                type = ModelType.MEMO,
                text = entity.text,
                ingredient = entity.ingredient?.let {
                    Ingredient(
                        id = it.id,
                        title = it.title,
                        category = IngredientCategoryMapper.map(it.category),
                        image = it.image
                    )
                },
                isChecked = entity.isChecked
            )
        }
    }
}