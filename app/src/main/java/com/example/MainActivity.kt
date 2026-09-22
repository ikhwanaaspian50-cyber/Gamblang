package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.ArticleData
import com.example.model.AppTab
import com.example.ui.components.GamblangHeader
import com.example.ui.components.ReadingSettingsDialog
import com.example.ui.components.ReflectionDialog
import com.example.ui.components.SearchDialog
import com.example.ui.screens.ArticleReaderScreen
import com.example.ui.screens.CategoryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SavedScreen
import com.example.ui.theme.GamblangTheme
import com.example.ui.theme.GoldManuscript
import com.example.viewmodel.GamblangViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamblangApp()
        }
    }
}

@Composable
fun GamblangApp(viewModel: GamblangViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val bookmarkedIds by viewModel.bookmarkedArticleIds.collectAsStateWithLifecycle()
    val isDailyReflectionSaved by viewModel.isDailyReflectionSaved.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val isSearchActive by viewModel.isSearchActive.collectAsStateWithLifecycle()
    val readingSettings by viewModel.readingSettings.collectAsStateWithLifecycle()
    val activeArticle by viewModel.activeArticle.collectAsStateWithLifecycle()
    val showReflectionDialog by viewModel.showReflectionDialog.collectAsStateWithLifecycle()
    val showSettingsDialog by viewModel.showSettingsDialog.collectAsStateWithLifecycle()
    val isAudioPlaying by viewModel.isAudioPlaying.collectAsStateWithLifecycle()
    val audioCurrentSeconds by viewModel.audioCurrentSeconds.collectAsStateWithLifecycle()

    val filteredArticles = viewModel.filteredArticles()
    val bookmarkedArticles = viewModel.bookmarkedArticles()
    val featuredArticle = ArticleData.articles.find { it.isFeatured }

    GamblangTheme(darkTheme = readingSettings.isQuietMode) {
        // Handle hardware / gesture back button when article reader is open
        BackHandler(enabled = activeArticle != null) {
            viewModel.closeArticle()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    if (activeArticle == null) {
                        GamblangHeader(
                            onSearchClick = { viewModel.openSearch() },
                            onSettingsClick = { viewModel.openSettingsDialog() },
                            onProfileClick = { viewModel.selectTab(AppTab.PROFIL) },
                            savedCount = bookmarkedIds.size + if (isDailyReflectionSaved) 1 else 0,
                            onSavedClick = { viewModel.selectTab(AppTab.TERSIMPAN) }
                        )
                    }
                },
                bottomBar = {
                    if (activeArticle == null) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                )
                                .navigationBarsPadding()
                                .testTag("bottom_nav_bar")
                        ) {
                            AppTab.entries.forEach { tab ->
                                val isSelected = currentTab == tab
                                val icon = getTabIcon(tab)

                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = { viewModel.selectTab(tab) },
                                    icon = {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = tab.title,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = tab.title,
                                            fontFamily = FontFamily.SansSerif,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 11.sp
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = GoldManuscript,
                                        selectedTextColor = GoldManuscript,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    modifier = Modifier.testTag("tab_${tab.name.lowercase()}")
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (currentTab) {
                        AppTab.BERANDA -> {
                            HomeScreen(
                                articles = filteredArticles,
                                featuredArticle = featuredArticle,
                                selectedCategoryId = selectedCategory,
                                bookmarkedIds = bookmarkedIds,
                                isDailyReflectionSaved = isDailyReflectionSaved,
                                readingSettings = readingSettings,
                                isAudioPlaying = isAudioPlaying,
                                audioCurrentSeconds = audioCurrentSeconds,
                                onSelectCategory = { viewModel.selectCategory(it) },
                                onArticleClick = { viewModel.openArticle(it) },
                                onBookmarkToggle = { viewModel.toggleBookmark(it) },
                                onReflectionBookmarkToggle = { viewModel.toggleDailyReflectionBookmark() },
                                onOpenTadabbur = { viewModel.openReflectionDialog() },
                                onToggleAudio = { viewModel.toggleAudioPlayback() },
                                onToggleSerif = { viewModel.toggleSerifFont() },
                                onToggleQuietMode = { viewModel.toggleQuietMode() },
                                onOpenSettings = { viewModel.openSettingsDialog() },
                                onAuthorClick = { viewModel.selectTab(AppTab.PROFIL) }
                            )
                        }

                        AppTab.KATEGORI -> {
                            CategoryScreen(
                                selectedCategoryId = selectedCategory,
                                filteredArticles = filteredArticles,
                                bookmarkedIds = bookmarkedIds,
                                onSelectCategory = { viewModel.selectCategory(it) },
                                onArticleClick = { viewModel.openArticle(it) },
                                onBookmarkToggle = { viewModel.toggleBookmark(it) },
                                useSerif = readingSettings.useSerif
                            )
                        }

                        AppTab.TERSIMPAN -> {
                            SavedScreen(
                                savedArticles = bookmarkedArticles,
                                isDailyReflectionSaved = isDailyReflectionSaved,
                                onArticleClick = { viewModel.openArticle(it) },
                                onBookmarkToggle = { viewModel.toggleBookmark(it) },
                                onReflectionBookmarkToggle = { viewModel.toggleDailyReflectionBookmark() },
                                onOpenTadabbur = { viewModel.openReflectionDialog() },
                                useSerif = readingSettings.useSerif
                            )
                        }

                        AppTab.PROFIL -> {
                            ProfileScreen(
                                readingSettings = readingSettings,
                                onToggleSerif = { viewModel.toggleSerifFont() },
                                onToggleQuietMode = { viewModel.toggleQuietMode() },
                                onOpenSettings = { viewModel.openSettingsDialog() }
                            )
                        }
                    }
                }
            }

            // Reader Screen Transition Overlay
            AnimatedVisibility(
                visible = activeArticle != null,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
            ) {
                activeArticle?.let { article ->
                    ArticleReaderScreen(
                        article = article,
                        isSaved = bookmarkedIds.contains(article.id),
                        readingSettings = readingSettings,
                        onBack = { viewModel.closeArticle() },
                        onSaveToggle = { viewModel.toggleBookmark(article.id) },
                        onOpenSettings = { viewModel.openSettingsDialog() },
                        onPlayAudio = { viewModel.toggleAudioPlayback() }
                    )
                }
            }

            // Search Overlay Dialog
            if (isSearchActive) {
                SearchDialog(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    results = filteredArticles,
                    onArticleClick = { viewModel.openArticle(it) },
                    onDismiss = { viewModel.closeSearch() }
                )
            }

            // Daily Reflection Tadabbur Dialog
            if (showReflectionDialog) {
                ReflectionDialog(
                    reflection = ArticleData.dailyReflection,
                    isSaved = isDailyReflectionSaved,
                    onSaveToggle = { viewModel.toggleDailyReflectionBookmark() },
                    onDismiss = { viewModel.closeReflectionDialog() }
                )
            }

            // Reading Comfort Settings Dialog
            if (showSettingsDialog) {
                ReadingSettingsDialog(
                    settings = readingSettings,
                    onToggleSerif = { viewModel.toggleSerifFont() },
                    onToggleQuietMode = { viewModel.toggleQuietMode() },
                    onFontScaleChange = { viewModel.setFontScale(it) },
                    onDismiss = { viewModel.closeSettingsDialog() }
                )
            }
        }
    }
}

private fun getTabIcon(tab: AppTab): ImageVector {
    return when (tab) {
        AppTab.BERANDA -> Icons.Default.AutoStories
        AppTab.KATEGORI -> Icons.Default.Category
        AppTab.TERSIMPAN -> Icons.Default.Bookmarks
        AppTab.PROFIL -> Icons.Default.Person
    }
}
