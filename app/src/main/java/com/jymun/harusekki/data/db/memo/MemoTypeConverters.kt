package com.jymun.harusekki.data.db.memo

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

class MemoTypeConverters {

    @TypeConverter
    fun ingredientToString(value: IngredientEntity?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToIngredient(value: String?): IngredientEntity? =
        Gson().fromJson(value, IngredientEntity::class.java)
}