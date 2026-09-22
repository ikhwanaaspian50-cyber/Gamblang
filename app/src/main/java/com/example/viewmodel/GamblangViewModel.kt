package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ArticleData
import com.example.model.AppTab
import com.example.model.Article
import com.example.model.ReadingSettings
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamblangViewModel : ViewModel() {

    private val _currentTab = MutableStateFlow(AppTab.BERANDA)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    private val _selectedCategory = MutableStateFlow("all")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _bookmarkedArticleIds = MutableStateFlow(setOf("mengenal-getaran-qolbu"))
    val bookmarkedArticleIds: StateFlow<Set<String>> = _bookmarkedArticleIds.asStateFlow()

    private val _isDailyReflectionSaved = MutableStateFlow(false)
    val isDailyReflectionSaved: StateFlow<Boolean> = _isDailyReflectionSaved.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive: StateFlow<Boolean> = _isSearchActive.asStateFlow()

    private val _readingSettings = MutableStateFlow(ReadingSettings())
    val readingSettings: StateFlow<ReadingSettings> = _readingSettings.asStateFlow()

    private val _activeArticle = MutableStateFlow<Article?>(null)
    val activeArticle: StateFlow<Article?> = _activeArticle.asStateFlow()

    private val _showReflectionDialog = MutableStateFlow(false)
    val showReflectionDialog: StateFlow<Boolean> = _showReflectionDialog.asStateFlow()

    private val _showSettingsDialog = MutableStateFlow(false)
    val showSettingsDialog: StateFlow<Boolean> = _showSettingsDialog.asStateFlow()

    // Audio Player State: "Dzikir & Getaran Qolbu (Sirrul Asrar)" (03:42 -> 222 seconds)
    private val totalAudioSeconds = 222
    private val _isAudioPlaying = MutableStateFlow(false)
    val isAudioPlaying: StateFlow<Boolean> = _isAudioPlaying.asStateFlow()

    private val _audioCurrentSeconds = MutableStateFlow(42) // starting sample 00:42
    val audioCurrentSeconds: StateFlow<Int> = _audioCurrentSeconds.asStateFlow()

    private var audioTimerJob: Job? = null

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun selectCategory(categoryId: String) {
        _selectedCategory.value = categoryId
    }

    fun toggleBookmark(articleId: String) {
        _bookmarkedArticleIds.update { current ->
            if (current.contains(articleId)) {
                current - articleId
            } else {
                current + articleId
            }
        }
    }

    fun toggleDailyReflectionBookmark() {
        _isDailyReflectionSaved.update { !it }
    }

    fun openArticle(article: Article) {
        _activeArticle.value = article
    }

    fun closeArticle() {
        _activeArticle.value = null
    }

    fun openSearch() {
        _isSearchActive.value = true
    }

    fun closeSearch() {
        _isSearchActive.value = false
        _searchQuery.value = ""
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun openReflectionDialog() {
        _showReflectionDialog.value = true
    }

    fun closeReflectionDialog() {
        _showReflectionDialog.value = false
    }

    fun openSettingsDialog() {
        _showSettingsDialog.value = true
    }

    fun closeSettingsDialog() {
        _showSettingsDialog.value = false
    }

    fun toggleSerifFont() {
        _readingSettings.update { it.copy(useSerif = !it.useSerif) }
    }

    fun toggleQuietMode() {
        _readingSettings.update { it.copy(isQuietMode = !it.isQuietMode) }
    }

    fun setFontScale(scale: Float) {
        _readingSettings.update { it.copy(fontScale = scale) }
    }

    fun toggleAudioPlayback() {
        if (_isAudioPlaying.value) {
            pauseAudio()
        } else {
            playAudio()
        }
    }

    private fun playAudio() {
        _isAudioPlaying.value = true
        audioTimerJob?.cancel()
        audioTimerJob = viewModelScope.launch {
            while (_isAudioPlaying.value) {
                delay(1000)
                _audioCurrentSeconds.update { current ->
                    if (current >= totalAudioSeconds) {
                        0
                    } else {
                        current + 1
                    }
                }
            }
        }
    }

    private fun pauseAudio() {
        _isAudioPlaying.value = false
        audioTimerJob?.cancel()
    }

    fun seekAudio(progressFraction: Float) {
        val targetSec = (progressFraction * totalAudioSeconds).toInt().coerceIn(0, totalAudioSeconds)
        _audioCurrentSeconds.value = targetSec
    }

    fun filteredArticles(): List<Article> {
        val category = _selectedCategory.value
        val query = _searchQuery.value.trim().lowercase()

        return ArticleData.articles.filter { article ->
            val matchesCategory = when (category) {
                "all" -> true
                "tasawuf" -> article.category.equals("Tasawuf", ignoreCase = true)
                "filologi" -> article.category.equals("Filologi", ignoreCase = true)
                "kisah_nabi" -> article.category.equals("Kisah Nabi", ignoreCase = true)
                "tokoh_sufi" -> article.category.equals("Tokoh Sufi", ignoreCase = true)
                "amalan" -> article.category.contains("Amalan", ignoreCase = true)
                "hakikat" -> article.category.contains("Hakikat", ignoreCase = true)
                else -> true
            }

            val matchesQuery = if (query.isEmpty()) {
                true
            } else {
                article.title.lowercase().contains(query) ||
                        article.summary.lowercase().contains(query) ||
                        article.category.lowercase().contains(query) ||
                        article.tags.any { it.lowercase().contains(query) } ||
                        (article.quranVerseTranslation?.lowercase()?.contains(query) == true)
            }

            matchesCategory && matchesQuery
        }
    }

    fun bookmarkedArticles(): List<Article> {
        val bookmarkedIds = _bookmarkedArticleIds.value
        return ArticleData.articles.filter { bookmarkedIds.contains(it.id) }
    }
}
