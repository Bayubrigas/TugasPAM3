package com.example.profileapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profileapp.ui.components.BioSection
import com.example.profileapp.ui.components.InfoItem
import com.example.profileapp.ui.components.ProfileCard
import com.example.profileapp.ui.components.ProfileHeader

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    var isFollowing by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // COMPOSABLE 1: ProfileHeader
        ProfileHeader(
            name = "Bayu Brigas Novaldi",
            role = "Mahasiswa Teknik Informatika Itera"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row dengan dua Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { isFollowing = !isFollowing },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if (isFollowing) Icons.Default.Check else Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = if (isFollowing) "Following" else "Follow")
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Pesan")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // COMPOSABLE 2: BioSection
        BioSection(
            bio = "Mahasiswa semester 6 yang sedang belajar pengembangan " +
                    "aplikasi mobile, belajar Kotlin Multiplatform dan " +
                    "Compose. Suka ngidding, ngematcha, dan main badminton."
        )

        // COMPOSABLE 3: ProfileCard + InfoItem - Kontak
        ProfileCard(title = "Informasi Kontak") {
            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = "bayu.123140072@student.itera.ac.id"
            )
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = "+62 857-8310-8974"
            )
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = "Bandar Lampung, Indonesia"
            )
        }

        // ProfileCard - Akademik
        ProfileCard(title = "Informasi Akademik") {
            InfoItem(
                icon = Icons.Default.Home,
                label = "Institusi",
                value = "Institut Teknologi Sumatera"
            )
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(
                icon = Icons.Default.List,
                label = "Program Studi",
                value = "Teknik Informatika"
            )
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(
                icon = Icons.Default.Person,
                label = "NIM",
                value = "123140072"
            )
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(
                icon = Icons.Default.DateRange,
                label = "Semester",
                value = "6 (Genap 2025/2026)"
            )
        }

        // ProfileCard - Keahlian
        ProfileCard(title = "Keahlian") {
            SkillBadgeRow(skills = listOf("Kotlin", "Jetpack Compose", "Android"))
            Spacer(modifier = Modifier.height(6.dp))
            SkillBadgeRow(skills = listOf("Git", "Firebase", "REST API"))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// ============================================================
// COMPOSABLE 5 (Bonus): SkillBadgeRow
// Baris badge keahlian menggunakan Row
// ============================================================
@Composable
fun SkillBadgeRow(skills: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        skills.forEach { skill ->
            SkillBadge(skill = skill)
        }
    }
}

// ============================================================
// COMPOSABLE 6 (Bonus): SkillBadge
// Badge tunggal untuk satu keahlian
// Menggunakan: Surface, Text, Box
// ============================================================
@Composable
fun SkillBadge(skill: String) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}