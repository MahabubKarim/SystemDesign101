package com.mmk.systemdesign.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimaryDark,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFE65100), // Darker Orange Container
    onPrimaryContainer = Color.White,
    secondary = OrangeSecondaryDark,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFEF6C00),
    onSecondaryContainer = Color.White,
    tertiary = OrangeTertiaryDark,
    onTertiary = Color.Black,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF1C1B1F),
    onBackground = Color.White,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF4E342E), // Brownish Orange for contrast
    onSurfaceVariant = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE0B2), // Light Orange Container
    onPrimaryContainer = Color(0xFFE65100), // Dark Orange Text
    secondary = OrangeSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFF3E0),
    onSecondaryContainer = Color(0xFFEF6C00),
    tertiary = OrangeTertiary,
    onTertiary = Color.White,
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFFFF8E1), // Very Light Orange
    onSurfaceVariant = Color(0xFF5D4037)
)

@Composable
fun SystemDesign101Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set dynamic color to false as user requested a specific orange theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
