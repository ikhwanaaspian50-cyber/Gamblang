package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ArticleData
import com.example.model.Article
import com.example.model.ReadingSettings
import com.example.ui.components.ArticleListItem
import com.example.ui.components.AudioWidget
import com.example.ui.components.AuthorCuratorCard
import com.example.ui.components.DailyReflectionCard
import com.example.ui.components.FeaturedArticleCard
import com.example.ui.theme.GoldManuscript

@Composable
fun HomeScreen(
    articles: List<Article>,
    featuredArticle: Article?,
    selectedCategoryId: String,
    bookmarkedIds: Set<String>,
    isDailyReflectionSaved: Boolean,
    readingSettings: ReadingSettings,
    isAudioPlaying: Boolean,
    audioCurrentSeconds: Int,
    onSelectCategory: (String) -> Unit,
    onArticleClick: (Article) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    onReflectionBookmarkToggle: () -> Unit,
    onOpenTadabbur: () -> Unit,
    onToggleAudio: () -> Unit,
    onToggleSerif: () -> Unit,
    onToggleQuietMode: () -> Unit,
    onOpenSettings: () -> Unit,
    onAuthorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Smooth scroll reading progress indicator for the home feed
    val scrollProgress by remember {
        derivedStateOf {
            val totalItems = listState.layoutInfo.totalItemsCount
            if (totalItems > 0) {
                val firstVisible = listState.firstVisibleItemIndex
                (firstVisible.toFloat() / (totalItems - 1).coerceAtLeast(1)).coerceIn(0f, 1f)
            } else 0f
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Subtle Hairline Reading Progress Bar
            LinearProgressIndicator(
                progress = { scrollProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.5.dp),
                color = GoldManuscript,
                trackColor = MaterialTheme.colorScheme.surface,
                strokeCap = StrokeCap.Square
            )

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(key = "space_top") {
                    Spacer(modifier = Modifier.height(4.dp))
                }

                // 1. Kutipan Renungan Hari Ini (Daily Reflection Card)
                item(key = "daily_reflection") {
                    DailyReflectionCard(
                        reflection = ArticleData.dailyReflection,
                        isSaved = isDailyReflectionSaved,
                        onTadabburClick = onOpenTadabbur,
                        onSaveToggle = onReflectionBookmarkToggle
                    )
                }

                // 2. Kategori Horizontal Chips Filter
                item(key = "category_chips") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ArticleData.categories.forEach { cat ->
                            val isSelected = cat.id == selectedCategoryId
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                        shape = CircleShape
                                    )
                                    .clickable { onSelectCategory(cat.id) }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                                    .testTag("category_chip_${cat.id}")
                            ) {
                                Text(
                                    text = cat.name,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    fontSize = 12.sp,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // 3. Pilihan Utama (Featured Article)
                if (featuredArticle != null && (selectedCategoryId == "all" || selectedCategoryId == "tasawuf")) {
                    item(key = "featured_article") {
                        FeaturedArticleCard(
                            article = featuredArticle,
                            isSaved = bookmarkedIds.contains(featuredArticle.id),
                            onReadClick = { onArticleClick(featuredArticle) },
                            onPlayAudioClick = onToggleAudio,
                            onSaveToggle = { onBookmarkToggle(featuredArticle.id) }
                        )
                    }
                }

                // 4. Widget Audio & Kenyamanan Baca
                item(key = "audio_widget") {
                    AudioWidget(
                        isPlaying = isAudioPlaying,
                        currentSeconds = audioCurrentSeconds,
                        totalSeconds = 222, // 03:42
                        readingSettings = readingSettings,
                        onTogglePlay = onToggleAudio,
                        onToggleSerif = onToggleSerif,
                        onToggleQuietMode = onToggleQuietMode,
                        onOpenSettings = onOpenSettings
                    )
                }

                // 5. Header Renungan Terbaru / Arsip Manuskrip
                item(key = "feed_header") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RENUNGAN TERBARU",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 1.2.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Arsip Manuskrip",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // 6. Feed Naskah Asli Blog
                val nonFeaturedArticles = articles.filter { !it.isFeatured || (selectedCategoryId != "all" && selectedCategoryId != "tasawuf") }
                items(nonFeaturedArticles, key = { it.id }) { article ->
                    ArticleListItem(
                        article = article,
                        isSaved = bookmarkedIds.contains(article.id),
                        onClick = { onArticleClick(article) },
                        onSaveToggle = { onBookmarkToggle(article.id) },
                        useSerif = readingSettings.useSerif
                    )
                }

                // 7. Profil Kurator & Penulis (Kang Iwe)
                item(key = "author_card") {
                    AuthorCuratorCard(onCardClick = onAuthorClick)
                }

                // 8. Colophon Note
                item(key = "colophon_footer") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(1.dp)
                                    .background(GoldManuscript.copy(alpha = 0.4f))
                            )
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = GoldManuscript,
                                modifier = Modifier.size(15.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(1.dp)
                                    .background(GoldManuscript.copy(alpha = 0.4f))
                            )
                        }
                        Text(
                            text = "Gamblang Folio • Terawat Sejak 2009",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }

                item(key = "space_bottom") {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
