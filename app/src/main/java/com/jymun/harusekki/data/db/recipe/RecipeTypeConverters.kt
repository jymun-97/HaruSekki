package com.jymun.harusekki.data.db.recipe

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.jymun.harusekki.data.entity.cooking_step.CookingStepEntity
import com.jymun.harusekki.data.entity.ingredient.IngredientEntity

class RecipeTypeConverters {

    @TypeConverter
    fun imgListToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToImgList(value: String): List<String> =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun ingredientListToJson(value: List<IngredientEntity>?): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonToIngredientList(value: String?): List<IngredientEntity>? =
        Gson().fromJson(value, Array<IngredientEntity>::class.java)?.toList()

    @TypeConverter
    fun cookingStepListToJson(value: List<CookingStepEntity>?): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonToCookingStepList(value: String?): List<CookingStepEntity>? =
        Gson().fromJson(value, Array<CookingStepEntity>::class.java)?.toList()

}