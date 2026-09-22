package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Article
import com.example.model.ReadingSettings
import com.example.ui.theme.ArabicAccent
import com.example.ui.theme.BorderPapyrus
import com.example.ui.theme.GoldLeaf
import com.example.ui.theme.GoldManuscript

@Composable
fun ArticleReaderScreen(
    article: Article,
    isSaved: Boolean,
    readingSettings: ReadingSettings,
    onBack: () -> Unit,
    onSaveToggle: () -> Unit,
    onOpenSettings: () -> Unit,
    onPlayAudio: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    // Smooth reading progress calculation
    val readingProgress by remember {
        derivedStateOf {
            val totalItems = listState.layoutInfo.totalItemsCount
            if (totalItems > 0) {
                val firstVisible = listState.firstVisibleItemIndex
                (firstVisible.toFloat() / (totalItems - 1).coerceAtLeast(1)).coerceIn(0f, 1f)
            } else 0f
        }
    }

    val baseFontSize = 16.sp * readingSettings.fontScale
    val baseLineHeight = (28.sp * readingSettings.fontScale)
    val chosenFontFamily = if (readingSettings.useSerif) FontFamily.Serif else FontFamily.SansSerif

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Sticky Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("reader_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        // Quick Audio Trigger
                        IconButton(
                            onClick = onPlayAudio,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolumeUp,
                                contentDescription = "Dengarkan",
                                tint = GoldManuscript,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Reading Comfort Font Settings
                        IconButton(
                            onClick = onOpenSettings,
                            modifier = Modifier.size(38.dp).testTag("reader_font_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.FormatSize,
                                contentDescription = "Ukuran Teks",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Bookmark Action
                        IconButton(
                            onClick = onSaveToggle,
                            modifier = Modifier.size(38.dp).testTag("reader_bookmark_btn")
                        ) {
                            Icon(
                                imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Simpan",
                                tint = if (isSaved) GoldLeaf else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Share Action
                        IconButton(
                            onClick = {
                                try {
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_SUBJECT, article.title)
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "\"${article.title}\"\n\n${article.summary}\n\nBaca di Gamblang Folio: https://234byte.blogspot.com/"
                                        )
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Bagikan Naskah"))
                                } catch (_: Exception) { }
                            },
                            modifier = Modifier.size(38.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Bagikan",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(19.dp)
                            )
                        }
                    }
                }
            }

            // Hairline Scroll Progress Bar
            LinearProgressIndicator(
                progress = { readingProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.5.dp),
                color = GoldManuscript,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = StrokeCap.Square
            )

            // Article Content Flow
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(key = "header_space") {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Metadata Pill
                item(key = "meta_info") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = article.category,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldManuscript
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "${article.readTimeMinutes} menit baca",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Article Title in Large Editorial Serif
                item(key = "article_title") {
                    Text(
                        text = article.title,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = (24.sp * readingSettings.fontScale),
                        lineHeight = (32.sp * readingSettings.fontScale),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                // Attribution & Source
                item(key = "source_attribution") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = GoldManuscript,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = article.source,
                            fontFamily = FontFamily.SansSerif,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Sacred Quranic Script Callout Block (if present)
                if (article.quranVerseArabic != null) {
                    item(key = "quran_callout") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .border(1.dp, BorderPapyrus, RoundedCornerShape(10.dp))
                                .padding(18.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = article.quranVerseArabic,
                                    fontFamily = FontFamily.Serif,
                                    fontSize = 21.sp,
                                    lineHeight = 38.sp,
                                    textAlign = TextAlign.Right,
                                    color = ArabicAccent,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                if (article.quranVerseTranslation != null) {
                                    Text(
                                        text = article.quranVerseTranslation,
                                        fontFamily = FontFamily.SansSerif,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 13.sp,
                                        lineHeight = 21.sp,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                if (article.quranVerseRef != null) {
                                    Text(
                                        text = article.quranVerseRef,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp,
                                        color = GoldManuscript,
                                        modifier = Modifier.align(Alignment.End)
                                    )
                                }
                            }
                        }
                    }
                }

                // Essay Body Paragraphs with generous line spacing
                itemsIndexed(article.contentParagraphs, key = { index, _ -> "para_$index" }) { _, paragraph ->
                    Text(
                        text = paragraph,
                        fontFamily = chosenFontFamily,
                        fontSize = baseFontSize,
                        lineHeight = baseLineHeight,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Tags Row
                item(key = "tags_row") {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        article.tags.forEach { tag ->
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .border(1.dp, BorderPapyrus, CircleShape)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "#$tag",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                // End of article colophon
                item(key = "end_note") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Gamblang Folio",
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "“Hanya catatan sedikit berarti untuk direnungkan dan dilaksanakan.”",
                                fontFamily = FontFamily.SansSerif,
                                fontStyle = FontStyle.Italic,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = GoldManuscript
                            )
                        }
                    }
                }

                item(key = "footer_space") {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
