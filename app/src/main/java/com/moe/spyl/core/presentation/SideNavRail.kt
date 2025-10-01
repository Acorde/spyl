package com.moe.spyl.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moe.spyl.core.domain.User
import com.moe.spyl.core.domain.UserType
import com.moe.spyl.core.presentation.models.NavItemAlignment
import com.moe.spyl.core.presentation.models.NavItemType
import com.moe.spyl.ui.theme.SpylTheme


private val RailBg = Color(0xFF121926)
private val RailIcon = Color(0xFFCBD5E1)
private val RailIconSelected = Color.White

@Composable
fun SideNavRail(
    widthClass: WindowWidthSizeClass,
    user: User,
    selectedNavItem: NavItemType,
    onSelect: (NavItemType) -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = widthClass >= WindowWidthSizeClass.Medium
    val railWidth = if (expanded) 88.dp else 72.dp

    NavigationRail(
        modifier = modifier
            .width(railWidth)
            .fillMaxHeight(),
        containerColor = RailBg,
        contentColor = RailIcon
    ) {
        // Top logo
        Spacer(Modifier.height(0.dp))
        // group + sort once
        val (topItems, centerItems, bottomItems) = remember(user.navItemType) {
            val g = user.navItemType?.groupBy { it.navAlignment } // or it.navAlignment
            Triple(
                g?.get(NavItemAlignment.TOP).orEmpty().sortedBy { it.ordinal },
                g?.get(NavItemAlignment.CENTER).orEmpty().sortedBy { it.ordinal },
                g?.get(NavItemAlignment.BOTTOM).orEmpty().sortedBy { it.ordinal }
            )
        }

        // --- TOP ---
        Spacer(Modifier.height(8.dp))
        topItems.forEach { type ->
            NavItem(
                type = type,
                selectedNavItemType = selectedNavItem,
                icon = {
                    when {
                        type.icon != null -> {
                            Icon(
                                imageVector = type.icon,
                                contentDescription = type.label,
                                //tint = if (selected) RailIconSelected else RailIcon
                            )
                        }

                        type.image != null -> {
                            // If your drawable is monochrome and you want tinting, add ColorFilter.tint(...)
                            Image(
                                painter = painterResource(type.image),
                                contentDescription = type.label,
                                modifier = Modifier.size(24.dp),
                                // colorFilter = ColorFilter.tint(if (selected) RailIconSelected else RailIcon)
                            )
                        }
                    }
                },
                expanded = expanded,
                onSelect = onSelect
            )
        }

        // --- CENTER (vertically centered between top and bottom) ---
        Spacer(Modifier.weight(1f))          // pushes center group down
        centerItems.forEach { type ->
            NavItem(
                type = type,
                selectedNavItemType = selectedNavItem,
                icon = {
                    when {
                        type.icon != null -> {
                            Icon(
                                imageVector = type.icon,
                                contentDescription = type.label,
                                //tint = if (selected) RailIconSelected else RailIcon
                            )
                        }

                        type.image != null -> {
                            // If your drawable is monochrome and you want tinting, add ColorFilter.tint(...)
                            Image(
                                painter = painterResource(type.image),
                                contentDescription = type.label,
                                modifier = Modifier.size(24.dp),
                                // colorFilter = ColorFilter.tint(if (selected) RailIconSelected else RailIcon)
                            )
                        }
                    }
                },
                expanded = expanded,
                onSelect = onSelect
            )
        }
        Spacer(Modifier.weight(1f))          // pushes bottom group to the bottom
        // --- BOTTOM ---
        bottomItems.forEach { type ->
            NavItem(
                type = type,
                selectedNavItemType = selectedNavItem,
                icon = {
                    when {
                        type.icon != null -> {
                            Icon(
                                imageVector = type.icon,
                                contentDescription = type.label,
                                //tint = if (selected) RailIconSelected else RailIcon
                            )
                        }

                        type.image != null -> {
                            // If your drawable is monochrome and you want tinting, add ColorFilter.tint(...)
                            Image(
                                painter = painterResource(type.image),
                                contentDescription = type.label,
                                modifier = Modifier.size(24.dp),
                                // colorFilter = ColorFilter.tint(if (selected) RailIconSelected else RailIcon)
                            )
                        }
                    }
                },
                expanded = expanded,
                onSelect = onSelect
            )

        }
    }
}


@Composable
private fun NavItem(
    type: NavItemType,
    selectedNavItemType: NavItemType,
    icon: @Composable () -> Unit,
    expanded: Boolean,
    onSelect: (NavItemType) -> Unit
) {
    val selected = selectedNavItemType == type
    NavigationRailItem(
        selected = selected,
        onClick = { onSelect(type) },
        icon = icon,
        label = type.label?.let { { Text(it) } },
        alwaysShowLabel = true,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = RailIconSelected,
            selectedTextColor = RailIconSelected,
            indicatorColor = Color(0xFF0B1220),
            unselectedIconColor = RailIcon,
            unselectedTextColor = RailIcon
        )
    )
}


@Preview(name = "SideNavRail – Compact", widthDp = 360, heightDp = 600, showBackground = true)
@Composable
private fun Preview_SideNavRail_Compact() {
    var selected by remember { mutableStateOf(NavItemType.CHAT) }
    SpylTheme {
        // Give height so the rail's fillMaxHeight() has constraints
        Row(Modifier.height(560.dp)) {
            SideNavRail(
                widthClass = WindowWidthSizeClass.Compact,
                user = User(
                    userType = UserType.ADMIN,
                    navItemType = NavItemType.getNavItemUserType(UserType.ADMIN)
                ),
                selectedNavItem = selected,
                onSelect = { selected = it }
            )
            // Fake content area to the right
            Spacer(Modifier.width(16.dp))
            Box(Modifier.weight(1f))
        }
    }
}

@Preview(name = "SideNavRail – Medium", widthDp = 840, heightDp = 600, showBackground = true)
@Composable
private fun Preview_SideNavRail_Medium() {
    var selected by remember { mutableStateOf(NavItemType.SETTINGS) }
    SpylTheme {
        Row(Modifier.height(560.dp)) {
            SideNavRail(
                widthClass = WindowWidthSizeClass.Medium,
                user = User(
                    userType = UserType.RECEPTIONIST,
                    navItemType = NavItemType.getNavItemUserType(UserType.RECEPTIONIST)
                ),
                selectedNavItem = selected,
                onSelect = { selected = it }
            )
            Spacer(Modifier.width(16.dp))
            Box(Modifier.weight(1f))
        }
    }
}