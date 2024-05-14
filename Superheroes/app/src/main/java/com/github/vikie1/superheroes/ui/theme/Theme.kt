package com.github.vikie1.superheroes.ui.theme

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFABD468),
    onPrimary = Color(0xFF223600),
    primaryContainer = Color(0xFF344E00),
    onPrimaryContainer = Color(0xFFC6F181),
    secondary = Color(0xFFC1CAAB),
    onSecondary = Color(0xFF2B331D),
    secondaryContainer = Color(0xFF414A32),
    onSecondaryContainer = Color(0xFFDDE6C6),
    tertiary = Color(0xFFA0D0CA),
    onTertiary = Color(0xFF013733),
    tertiaryContainer = Color(0xFF1F4E4A),
    onTertiaryContainer = Color(0xFFBCECE6),
    error = Color(0xFFFFB4AB),
    errorContainer = Color(0xFF93000A),
    onError = Color(0xFF690005),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1B1C18),
    onBackground = Color(0xFFE4E3DB),
    surface = Color(0xFF1B1C18),
    onSurface = Color(0xFFE4E3DB),
    surfaceVariant = Color(0xFF45483D),
    onSurfaceVariant = Color(0xFFC5C8B9),
    outline = Color(0xFF8F9285),
    inverseOnSurface = Color(0xFF1B1C18),
    inverseSurface = Color(0xFFE4E3DB),
    inversePrimary = Color(0xFF466800),
    surfaceTint = Color(0xFFABD468),
    outlineVariant = Color(0xFF45483D),
    scrim = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF466800),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFC6F181),
    onPrimaryContainer = Color(0xFF121F00),
    secondary = Color(0xFF596248),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFDDE6C6),
    onSecondaryContainer = Color(0xFF161E0A),
    tertiary = Color(0xFF396661),
    onTertiary = Color(0xFFFFFFFF),
    onTertiaryContainer = Color(0xFF00201D),
    error = Color(0xFFBA1A1A),
    errorContainer = Color(0xFFFFDAD6),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFEFCF5),
    onBackground = Color(0xFF1B1C18),
    surface = Color(0xFFFEFCF5),
    onSurface = Color(0xFF1B1C18),
    surfaceVariant = Color(0xFFE1E4D4),
    onSurfaceVariant = Color(0xFF45483D),
    outline = Color(0xFF75786C),
    inverseOnSurface = Color(0xFFF2F1E9),
    inversePrimary = Color(0xFFABD468),
    inverseSurface = Color(0xFF30312C),
    surfaceTint = Color(0xFF466800),
    outlineVariant = Color(0xFFC5C8B9),
    scrim = Color(0xFF000000)
)

@Composable
fun SuperheroesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            setUpEdgeToEdge(view, darkTheme)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

/**
 * Sets up edge-to-edge for the window of this [view]. The system icon colors are set to either
 * light or dark depending on whether the [darkTheme] is enabled or not.
 */
private fun setUpEdgeToEdge(view: View, darkTheme: Boolean) {
    val window = (view.context as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.Transparent.toArgb()
    val navigationBarColor = Color.Transparent.toArgb()
    window.navigationBarColor = navigationBarColor
    val controller = WindowCompat.getInsetsController(window, view)
    controller.isAppearanceLightStatusBars = !darkTheme
    controller.isAppearanceLightNavigationBars = !darkTheme
}