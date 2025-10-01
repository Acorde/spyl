package com.moe.spyl.core.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.moe.spyl.R
import com.moe.spyl.core.domain.UserRole
import com.moe.spyl.core.domain.UserType

enum class NavItemType(
    val navAlignment: NavItemAlignment,
    val label: String? = null,
    val image: Int? = null,
    val icon: ImageVector? = null
) {
    LOGO(NavItemAlignment.TOP, image = R.drawable.ic_launcher_foreground),
    CHAT(NavItemAlignment.TOP, icon = Icons.AutoMirrored.Filled.Chat),
    FILES(NavItemAlignment.TOP, icon = Icons.Default.Folder),
    EMPLOYEES(NavItemAlignment.TOP, icon = Icons.Default.Groups),
    USER(NavItemAlignment.BOTTOM, image = R.drawable.ic_launcher_foreground),
    SETTINGS(NavItemAlignment.BOTTOM, icon = Icons.Default.Settings),
    LOG_OUT(NavItemAlignment.BOTTOM, icon = Icons.AutoMirrored.Filled.Logout);

    companion object {
        fun getNavItemUserType(type: UserType): List<NavItemType>? {
            return when (type) {
                UserType.ADMIN -> NavItemType.entries.toList()
                UserType.RECEPTIONIST -> listOf(
                    LOGO,
                    CHAT,
                    FILES,
                    USER,
                    LOG_OUT
                )

                else -> null
            }
        }
    }
}

enum class NavItemAlignment(val priority: Int) {
    TOP(0),
    CENTER(1),
    BOTTOM(2)

}