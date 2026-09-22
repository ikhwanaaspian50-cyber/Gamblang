package com.example.model

data class Article(
    val id: String,
    val title: String,
    val category: String,
    val readTimeMinutes: Int,
    val source: String,
    val publishDate: String,
    val summary: String,
    val quranVerseArabic: String? = null,
    val quranVerseTranslation: String? = null,
    val quranVerseRef: String? = null,
    val contentParagraphs: List<String>,
    val tags: List<String>,
    val isFeatured: Boolean = false
)

data class DailyReflection(
    val id: String,
    val quote: String,
    val author: String,
    val bookTitle: String,
    val readTime: String,
    val commentary: String
)

data class ReadingSettings(
    val useSerif: Boolean = true,
    val isQuietMode: Boolean = false,
    val fontScale: Float = 1.0f // 0.9f, 1.0f, 1.15f, 1.3f
)

enum class AppTab(val title: String, val iconName: String) {
    BERANDA("Beranda", "auto_stories"),
    KATEGORI("Kategori", "category"),
    TERSIMPAN("Tersimpan", "bookmark"),
    PROFIL("Profil", "person")
}

data class CategoryItem(
    val id: String,
    val name: String,
    val count: Int,
    val description: String,
    val iconName: String
)
