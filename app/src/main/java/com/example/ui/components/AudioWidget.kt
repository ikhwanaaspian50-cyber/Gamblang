package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ReadingSettings
import com.example.ui.theme.GoldManuscript
import java.util.Locale

@Composable
fun AudioWidget(
    isPlaying: Boolean,
    currentSeconds: Int,
    totalSeconds: Int,
    readingSettings: ReadingSettings,
    onTogglePlay: () -> Unit,
    onToggleSerif: () -> Unit,
    onToggleQuietMode: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val progress = if (totalSeconds > 0) (currentSeconds.toFloat() / totalSeconds).coerceIn(0f, 1f) else 0f
    val currentFormatted = String.format(Locale.getDefault(), "%02d:%02d", currentSeconds / 60, currentSeconds % 60)
    val totalFormatted = String.format(Locale.getDefault(), "%02d:%02d", totalSeconds / 60, totalSeconds % 60)

    val playBtnColor by animateColorAsState(
        targetValue = if (isPlaying) GoldManuscript else MaterialTheme.colorScheme.primary,
        label = "audio_btn_color"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
            .testTag("audio_comfort_widget")
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Widget Title Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Headphones,
                        contentDescription = null,
                        tint = GoldManuscript,
                        modifier = Modifier.size(17.dp)
                    )
                    Text(
                        text = "AUDIO REFLEKSI SINGKAT",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.2.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = "$currentFormatted / $totalFormatted",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Audio Player Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            // Play / Pause round button
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(playBtnColor)
                                    .clickable { onTogglePlay() }
                                    .testTag("audio_play_pause_btn"),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isPlaying) "Jeda Audio" else "Putar Audio",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Dzikir & Getaran Qolbu (Sirrul Asrar)",
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1
                                )
                                Text(
                                    text = "Dipandu oleh Gamblang Folio",
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.GraphicEq,
                            contentDescription = null,
                            tint = if (isPlaying) GoldManuscript else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Progress Track
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = GoldManuscript,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            // Quick Reading Comfort Toolbar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kenyamanan Baca:",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Serif Toggle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (readingSettings.useSerif) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                            )
                            .border(
                                width = 1.dp,
                                color = if (readingSettings.useSerif) GoldManuscript else MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { onToggleSerif() }
                            .padding(horizontal = 9.dp, vertical = 5.dp)
                            .testTag("toggle_serif_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FormatSize,
                                contentDescription = null,
                                tint = if (readingSettings.useSerif) GoldManuscript else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (readingSettings.useSerif) "Serif" else "Sans",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (readingSettings.useSerif) GoldManuscript else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Quiet / Dark Mode Toggle
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (readingSettings.isQuietMode) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                            )
                            .border(
                                width = 1.dp,
                                color = if (readingSettings.isQuietMode) GoldManuscript else MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clickable { onToggleQuietMode() }
                            .padding(horizontal = 9.dp, vertical = 5.dp)
                            .testTag("toggle_hening_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = if (readingSettings.isQuietMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                                contentDescription = null,
                                tint = if (readingSettings.isQuietMode) GoldManuscript else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = if (readingSettings.isQuietMode) "Hening" else "Terang",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (readingSettings.isQuietMode) GoldManuscript else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Full Comfort Settings
                    IconButton(
                        onClick = onOpenSettings,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                            .testTag("open_comfort_settings_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Pengaturan",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}
