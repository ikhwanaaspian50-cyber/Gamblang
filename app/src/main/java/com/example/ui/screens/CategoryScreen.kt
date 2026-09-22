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
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ArticleData
import com.example.model.Article
import com.example.ui.components.ArticleListItem
import com.example.ui.theme.GoldManuscript

@Composable
fun CategoryScreen(
    selectedCategoryId: String,
    filteredArticles: List<Article>,
    bookmarkedIds: Set<String>,
    onSelectCategory: (String) -> Unit,
    onArticleClick: (Article) -> Unit,
    onBookmarkToggle: (String) -> Unit,
    useSerif: Boolean,
    modifier: Modifier = Modifier
) {
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
                    text = "Khazanah Kategori",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Telusuri naskah kuno, suluk tasawuf, dan sirah auliya",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Category Cards Grid/List
        items(ArticleData.categories, key = { it.id }) { cat ->
            val isSelected = cat.id == selectedCategoryId
            val icon = getCategoryIcon(cat.iconName)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    )
                    .border(
                        width = 1.dp,
                        color = if (isSelected) GoldManuscript else MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onSelectCategory(cat.id) }
                    .padding(14.dp)
                    .testTag("category_card_${cat.id}")
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) GoldManuscript else MaterialTheme.colorScheme.surfaceVariant
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else GoldManuscript,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cat.name,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "${cat.count} Naskah",
                                fontSize = 11.sp,
                                color = if (isSelected) GoldManuscript else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Text(
                            text = cat.description,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // Section Title for Articles in this Category
        item(key = "articles_header") {
            Spacer(modifier = Modifier.height(6.dp))
            val currentCatName = ArticleData.categories.find { it.id == selectedCategoryId }?.name ?: "Semua"
            Text(
                text = "TULISAN DALAM '$currentCatName' (${filteredArticles.size})",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 1.1.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Articles in selected category
        items(filteredArticles, key = { it.id }) { article ->
            ArticleListItem(
                article = article,
                isSaved = bookmarkedIds.contains(article.id),
                onClick = { onArticleClick(article) },
                onSaveToggle = { onBookmarkToggle(article.id) },
                useSerif = useSerif
            )
        }

        item(key = "bottom_space") {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun getCategoryIcon(name: String): ImageVector {
    return when (name) {
        "favorite" -> Icons.Default.Favorite
        "history_edu" -> Icons.Default.HistoryEdu
        "menu_book" -> Icons.Default.MenuBook
        "person" -> Icons.Default.Person
        "spa" -> Icons.Default.Spa
        "psychology" -> Icons.Default.Psychology
        else -> Icons.Default.Category
    }
}
