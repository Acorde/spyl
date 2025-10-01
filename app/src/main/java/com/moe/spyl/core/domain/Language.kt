package com.moe.spyl.core.domain

data class Language(
    val code: String,
    val name: String,
    val nativeName: String,
    val icon: String? = null
)
