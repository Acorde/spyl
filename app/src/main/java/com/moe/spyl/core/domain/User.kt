package com.moe.spyl.core.domain

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
    val language: Language? = null,
    val department: String? = null,
    val icon: String? = null,
    val userType: UserType? = null,
    val userRole: UserRole? = null
)
