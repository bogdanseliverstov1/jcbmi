package com.example.jcbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jcbmi.ui.components.AppleMinimalGlassBackground
import com.example.jcbmi.ui.components.GlassButton
import com.example.jcbmi.ui.components.GlassCard
import com.example.jcbmi.ui.components.GlassIconButton
import com.example.jcbmi.ui.components.GlassSlider
import com.example.jcbmi.ui.components.GlassTextField
import com.example.jcbmi.ui.components.GlassThemeToggleButton
import com.example.jcbmi.ui.theme.AppleStatusAlert
import com.example.jcbmi.ui.theme.AppleStatusInfo
import com.example.jcbmi.ui.theme.AppleStatusNormal
import com.example.jcbmi.ui.theme.AppleStatusWarning
import com.example.jcbmi.ui.theme.AppleTextMutedDark
import com.example.jcbmi.ui.theme.AppleTextMutedLight
import com.example.jcbmi.ui.theme.AppleTextPrimaryDark
import com.example.jcbmi.ui.theme.AppleTextPrimaryLight
import com.example.jcbmi.ui.theme.AppleTextSecondaryDark
import com.example.jcbmi.ui.theme.AppleTextSecondaryLight
import com.example.jcbmi.ui.theme.JcbmiTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(true) }

            JcbmiTheme(darkTheme = isDarkTheme) {
                Scaffold(containerColor = Color.Transparent) { innerPadding ->
                    AppleMinimalGlassBackground(
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        BMIscreen(
                            isDarkTheme = isDarkTheme,
                            onToggleTheme = { isDarkTheme = !isDarkTheme }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BMIscreen(
    isDarkTheme: Boolean = true,
    onToggleTheme: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableIntStateOf(80) }
    var height by remember { mutableIntStateOf(180) }
    var bmi by remember { mutableFloatStateOf(0f) }
    var calculated by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val textPrimary = if (isDarkTheme) AppleTextPrimaryDark else AppleTextPrimaryLight
    val textSecondary = if (isDarkTheme) AppleTextSecondaryDark else AppleTextSecondaryLight
    val textMuted = if (isDarkTheme) AppleTextMutedDark else AppleTextMutedLight

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Minimal Apple Title Bar with Theme Switcher Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "BMI Calculator",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = textPrimary,
                    letterSpacing = (-0.5).sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Minimal Glass",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = textSecondary,
                    letterSpacing = 0.5.sp
                )
            }

            // Theme Toggle Button
            GlassThemeToggleButton(
                isDarkTheme = isDarkTheme,
                onToggle = onToggleTheme
            )
        }

        // Card 1: User Profile / Name
        GlassCard(
            isDarkTheme = isDarkTheme,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Personal Information",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = textSecondary
                )
                GlassTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Name",
                    placeholder = "Enter your name",
                    isDarkTheme = isDarkTheme
                )
            }
        }

        // Card 2: Weight Selection
        GlassCard(
            isDarkTheme = isDarkTheme,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Weight (Pes)",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = textSecondary
                        )
                        Text(
                            text = "$weight kg",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = textPrimary
                        )
                    }

                    // Stepper Buttons (+ / -)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlassIconButton(
                            onClick = { if (weight > 20) weight-- },
                            symbol = "-",
                            isDarkTheme = isDarkTheme
                        )
                        GlassIconButton(
                            onClick = { if (weight < 300) weight++ },
                            symbol = "+",
                            isDarkTheme = isDarkTheme
                        )
                    }
                }
            }
        }

        // Card 3: Height Selection
        GlassCard(
            isDarkTheme = isDarkTheme,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Height",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = textSecondary
                    )
                    Text(
                        text = "$height cm",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textPrimary
                    )
                }

                GlassSlider(
                    value = height.toFloat(),
                    onValueChange = { height = it.toInt() },
                    valueRange = 100f..250f,
                    isDarkTheme = isDarkTheme
                )
            }
        }

        // Action Button: Calculate BMI
        GlassButton(
            onClick = {
                val heightInM = height / 100f
                if (heightInM > 0) {
                    bmi = weight / (heightInM * heightInM)
                    calculated = true
                }
            },
            isDarkTheme = isDarkTheme,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        ) {
            Text(
                text = "Calculate BMI",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDarkTheme) Color.Black else Color.White
            )
        }

        // Card 4: BMI Result
        if (calculated || bmi > 0f) {
            val (statusText, statusColor) = when {
                bmi < 18.5f -> "Underweight" to AppleStatusInfo
                bmi < 25.0f -> "Normal weight" to AppleStatusNormal
                bmi < 30.0f -> "Overweight" to AppleStatusWarning
                else -> "Obese" to AppleStatusAlert
            }

            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically(initialOffsetY = { 30 })
            ) {
                GlassCard(
                    isDarkTheme = isDarkTheme,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (name.isNotBlank()) {
                            Text(
                                text = "Results for $name",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = textSecondary
                            )
                        }

                        Text(
                            text = String.format(Locale.US, "%.1f", bmi),
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = textPrimary
                        )

                        // Minimal Status Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(statusColor.copy(alpha = 0.18f))
                                .padding(horizontal = 14.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = statusText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = statusColor
                            )
                        }

                        // Minimal Progress Indicator
                        val progress = ((bmi - 12f) / (40f - 12f)).coerceIn(0f, 1f)
                        val trackColor = if (isDarkTheme) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.08f)
                        val barColor = if (isDarkTheme) Color.White else Color.Black

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(trackColor)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(progress)
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(barColor)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("18.5", fontSize = 11.sp, color = textMuted)
                                Text("25.0", fontSize = 11.sp, color = textMuted)
                                Text("30.0", fontSize = 11.sp, color = textMuted)
                            }
                        }

                        Text(
                            text = "Your BMI is " + String.format(Locale.US, "%.2f", bmi) + " kg/m²",
                            fontSize = 13.sp,
                            color = textMuted,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewDark() {
    JcbmiTheme(darkTheme = true) {
        AppleMinimalGlassBackground(isDarkTheme = true) {
            BMIscreen(isDarkTheme = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewLight() {
    JcbmiTheme(darkTheme = false) {
        AppleMinimalGlassBackground(isDarkTheme = false) {
            BMIscreen(isDarkTheme = false)
        }
    }
}
