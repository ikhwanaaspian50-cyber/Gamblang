package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.GoldManuscript
import com.example.ui.theme.InkMuted

@Composable
fun GamblangHeader(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onProfileClick: () -> Unit,
    savedCount: Int,
    onSavedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Title Branding
            Column(
                modifier = Modifier
                    .clickable { onProfileClick() }
                    .testTag("brand_header")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Gamblang",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        letterSpacing = (-0.3).sp
                    )
                    Text(
                        text = "• FOLIO",
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                        color = GoldManuscript,
                        letterSpacing = 1.2.sp
                    )
                }
                Text(
                    text = "Catatan Renungan & Hikmah",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Right Action Controls
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                // Search Action Button
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier
                        .size(42.dp)
                        .testTag("header_search_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pencarian Naskah",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(22.dp)
                    )
                }

                // Comfort/Reading Settings Action
                IconButton(
                    onClick = onSettingsClick,
                    modifier = Modifier
                        .size(42.dp)
                        .testTag("header_settings_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Pengaturan Kenyamanan Baca",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Bookmarks Quick Access
                IconButton(
                    onClick = onSavedClick,
                    modifier = Modifier
                        .size(42.dp)
                        .testTag("header_bookmark_btn")
                ) {
                    Icon(
                        imageVector = if (savedCount > 0) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Tersimpan",
                        tint = if (savedCount > 0) GoldManuscript else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(21.dp)
                    )
                }

                // Author Profile Avatar
                Box(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(34.dp)
                        .clip(CircleShape)
                        .border(1.dp, GoldManuscript.copy(alpha = 0.6f), CircleShape)
                        .clickable { onProfileClick() }
                        .testTag("header_avatar_btn"),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_author_kang_iwe),
                        contentDescription = "Profil Kang Iwe",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }
    }
}
