package com.example.jcbmi.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcbmi.ui.theme.AppleBackgroundDark
import com.example.jcbmi.ui.theme.AppleBackgroundLight
import com.example.jcbmi.ui.theme.AppleGlassBorderDark
import com.example.jcbmi.ui.theme.AppleGlassBorderLight
import com.example.jcbmi.ui.theme.AppleGlassSurfaceDark
import com.example.jcbmi.ui.theme.AppleGlassSurfaceLight
import com.example.jcbmi.ui.theme.AppleTextMutedDark
import com.example.jcbmi.ui.theme.AppleTextMutedLight
import com.example.jcbmi.ui.theme.AppleTextPrimaryDark
import com.example.jcbmi.ui.theme.AppleTextPrimaryLight
import com.example.jcbmi.ui.theme.AppleTextSecondaryDark
import com.example.jcbmi.ui.theme.AppleTextSecondaryLight

/**
 * Minimal Apple Background (Clean, solid dark or light background)
 */
@Composable
fun AppleMinimalGlassBackground(
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val bgColor by animateColorAsState(
        targetValue = if (isDarkTheme) AppleBackgroundDark else AppleBackgroundLight,
        animationSpec = tween(400),
        label = "bg_color"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        content()
    }
}

/**
 * Minimal Frosted Glass Card Container (Adaptive Dark / Light Glass Surface)
 */
@Composable
fun GlassCard(
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    backgroundColor: Color = if (isDarkTheme) AppleGlassSurfaceDark else AppleGlassSurfaceLight,
    borderColor: Color = if (isDarkTheme) AppleGlassBorderDark else AppleGlassBorderLight,
    borderWidth: Dp = 1.dp,
    elevation: Dp = 6.dp,
    content: @Composable () -> Unit
) {
    val animatedBg by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(400),
        label = "card_bg"
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                ambientColor = if (isDarkTheme) Color.Black.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.08f),
                spotColor = if (isDarkTheme) Color.Black.copy(alpha = 0.3f) else Color.Black.copy(alpha = 0.04f)
            )
            .clip(shape)
            .background(animatedBg)
            .border(
                width = borderWidth,
                brush = Brush.linearGradient(
                    colors = listOf(
                        borderColor,
                        borderColor.copy(alpha = 0.10f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                shape = shape
            )
            .padding(1.dp)
    ) {
        content()
    }
}

/**
 * Minimal Apple Glass Input Field
 */
@Composable
fun GlassTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    val textColor = if (isDarkTheme) AppleTextPrimaryDark else AppleTextPrimaryLight
    val labelColor = if (isDarkTheme) AppleTextSecondaryDark else AppleTextSecondaryLight
    val placeholderColor = if (isDarkTheme) AppleTextMutedDark else AppleTextMutedLight

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = labelColor,
                fontSize = 14.sp
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = placeholderColor,
                fontSize = 14.sp
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isDarkTheme) Color.White.copy(alpha = 0.60f) else Color.Black.copy(alpha = 0.50f),
            unfocusedBorderColor = if (isDarkTheme) Color.White.copy(alpha = 0.18f) else Color.Black.copy(alpha = 0.15f),
            focusedContainerColor = if (isDarkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.80f),
            unfocusedContainerColor = if (isDarkTheme) Color.White.copy(alpha = 0.04f) else Color.White.copy(alpha = 0.50f),
            focusedLabelColor = textColor,
            unfocusedLabelColor = labelColor,
            cursorColor = textColor
        ),
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        modifier = modifier.fillMaxWidth()
    )
}

/**
 * Minimal Apple Glass Button (Clean high-contrast action pill)
 */
@Composable
fun GlassButton(
    onClick: () -> Unit,
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.97f else 1f

    val containerColor = if (isDarkTheme) Color.White else Color(0xFF1C1C1E)
    val contentColor = if (isDarkTheme) Color.Black else Color.White

    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(50)
            )
    ) {
        Box(
            modifier = Modifier.padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

/**
 * Minimal Circular Glass Stepper Button for + / -
 */
@Composable
fun GlassIconButton(
    onClick: () -> Unit,
    symbol: String,
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.92f else 1f

    val bgColor = if (isDarkTheme) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.85f)
    val borderColor = if (isDarkTheme) Color.White.copy(alpha = 0.22f) else Color.Black.copy(alpha = 0.10f)
    val textColor = if (isDarkTheme) AppleTextPrimaryDark else AppleTextPrimaryLight

    Box(
        modifier = modifier
            .size(44.dp)
            .scale(scale)
            .shadow(
                elevation = if (isDarkTheme) 2.dp else 4.dp,
                shape = CircleShape,
                ambientColor = Color.Black.copy(alpha = 0.1f)
            )
            .clip(CircleShape)
            .background(bgColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = CircleShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Minimal Glass Slider for Height
 */
@Composable
fun GlassSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    isDarkTheme: Boolean = true,
    modifier: Modifier = Modifier
) {
    val activeColor = if (isDarkTheme) Color.White else Color(0xFF1C1C1E)
    val inactiveColor = if (isDarkTheme) Color.White.copy(alpha = 0.18f) else Color.Black.copy(alpha = 0.12f)

    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        colors = SliderDefaults.colors(
            thumbColor = activeColor,
            activeTrackColor = activeColor,
            inactiveTrackColor = inactiveColor
        ),
        modifier = modifier
    )
}

/**
 * Minimal Glass Theme Toggle Button (Light / Dark mode switcher)
 */
@Composable
fun GlassThemeToggleButton(
    isDarkTheme: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale = if (isPressed) 0.94f else 1f

    val bgColor = if (isDarkTheme) Color.White.copy(alpha = 0.14f) else Color.White.copy(alpha = 0.85f)
    val borderColor = if (isDarkTheme) Color.White.copy(alpha = 0.25f) else Color.Black.copy(alpha = 0.12f)
    val textColor = if (isDarkTheme) AppleTextPrimaryDark else AppleTextPrimaryLight

    Box(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(50),
                ambientColor = Color.Black.copy(alpha = 0.1f)
            )
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onToggle
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isDarkTheme) "🌙" else "☀️",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = if (isDarkTheme) "Dark" else "Light",
                color = textColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
