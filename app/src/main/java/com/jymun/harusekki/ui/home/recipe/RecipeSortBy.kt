package com.jymun.harusekki.ui.home.recipe

enum class RecipeSortBy(val value: String) {
    OLD("oldest"),
    LATEST("latest"),
    HITS_ASC("hitsasc"),
    HITS_DESC("hitsdesc"),
    LIKES_ASC("likeasc"),
    LIKES_DESC("likedesc")
}