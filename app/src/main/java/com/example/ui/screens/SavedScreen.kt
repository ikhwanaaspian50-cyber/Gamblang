package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ArticleData
import com.example.model.Article
import com.example.ui.components.ArticleListItem
import com.example.ui.theme.BorderPapyrus
import com.example.ui.theme.GoldManuscript

@Composable
fun SavedScreen(
    savedArticles: List<Article>,
    isDailyReflectionSaved: Boolean,
    onArticleClick: (Article) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    onReflectionBookmarkToggle: () -> Unit,
    onOpenTadabbur: () -> Unit,
    useSerif: Boolean,
    modifier: Modifier = Modifier
) {
    val hasSavedItems = savedArticles.isNotEmpty() || isDailyReflectionSaved

    if (!hasSavedItems) {
        // Thoughtful Empty State
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmarks,
                        contentDescription = null,
                        tint = GoldManuscript,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Text(
                    text = "Belum Ada Naskah Tersimpan",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Sentuh ikon penanda buku pada artikel atau kutipan hikmah untuk menyimpannya ke dalam lembar perenungan pribadi Anda.",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item(key = "header") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Lembar Tersimpan",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Koleksi tulisan dan kutipan terpilih untuk dibaca berulang",
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Saved Reflection Quote (if bookmarked)
            if (isDailyReflectionSaved) {
                item(key = "saved_reflection") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(1.dp, BorderPapyrus, RoundedCornerShape(12.dp))
                            .clickable { onOpenTadabbur() }
                            .padding(16.dp)
                            .testTag("saved_reflection_card")
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
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
                                        imageVector = Icons.Default.Flare,
                                        contentDescription = null,
                                        tint = GoldManuscript,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "KUTIPAN TERSIMPAN",
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                        letterSpacing = 1.1.sp,
                                        color = GoldManuscript
                                    )
                                }
                                Text(
                                    text = "Buka Tadabbur",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Text(
                                text = ArticleData.dailyReflection.quote,
                                fontFamily = FontFamily.Serif,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                lineHeight = 22.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "— ${ArticleData.dailyReflection.author}",
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Saved Articles
            if (savedArticles.isNotEmpty()) {
                item(key = "saved_articles_label") {
                    Text(
                        text = "ARTIKEL TERSIMPAN (${savedArticles.size})",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.1.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(savedArticles, key = { it.id }) { article ->
                    ArticleListItem(
                        article = article,
                        isSaved = true,
                        onClick = { onArticleClick(article) },
                        onSaveToggle = { onBookmarkToggle(article.id) },
                        useSerif = useSerif
                    )
                }
            }

            item(key = "bottom_spacer") {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
