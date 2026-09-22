package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ParchmentColorScheme = lightColorScheme(
    primary = InkCharcoal,
    onPrimary = SurfaceParchment,
    primaryContainer = SurfaceParchmentDeep,
    onPrimaryContainer = InkBlack,
    secondary = GoldManuscript,
    onSecondary = Color.White,
    secondaryContainer = GoldSoft,
    onSecondaryContainer = GoldManuscript,
    tertiary = InkMuted,
    onTertiary = Color.White,
    background = SurfaceParchment,
    onBackground = InkBlack,
    surface = SurfaceParchment,
    onSurface = InkCharcoal,
    surfaceVariant = SurfaceParchmentDeep,
    onSurfaceVariant = InkMuted,
    outline = BorderPapyrus,
    outlineVariant = BorderSubtle
)

private val HeningColorScheme = darkColorScheme(
    primary = HeningInkPrimary,
    onPrimary = HeningBackground,
    primaryContainer = HeningSurfaceElevated,
    onPrimaryContainer = HeningInkPrimary,
    secondary = GoldAmber,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF3B2A12),
    onSecondaryContainer = GoldAmber,
    tertiary = HeningInkMuted,
    onTertiary = Color.White,
    background = HeningBackground,
    onBackground = HeningInkPrimary,
    surface = HeningSurface,
    onSurface = HeningInkPrimary,
    surfaceVariant = HeningSurfaceElevated,
    onSurfaceVariant = HeningInkMuted,
    outline = HeningBorder,
    outlineVariant = Color(0xFF2E3137)
)

@Composable
fun GamblangTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = if (darkTheme) HeningColorScheme else ParchmentColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GamblangTypography,
        content = content
    )
}
