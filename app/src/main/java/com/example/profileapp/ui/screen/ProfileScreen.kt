package com.example.profileapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.profileapp.ui.components.BioSection
import com.example.profileapp.ui.components.DarkModeToggle
import com.example.profileapp.ui.components.EditProfileForm
import com.example.profileapp.ui.components.InfoItem
import com.example.profileapp.ui.components.ProfileCard
import com.example.profileapp.ui.components.ProfileHeader
import com.example.profileapp.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = viewModel()
) {
    // Collect StateFlow dari ViewModel
    val uiState by profileViewModel.uiState.collectAsStateWithLifecycle()

    // State lokal untuk form edit — state hoisting pattern
    // Nilai ini hanya hidup selama form ditampilkan
    var editName by remember(uiState.isEditMode) { mutableStateOf(uiState.name) }
    var editBio by remember(uiState.isEditMode) { mutableStateOf(uiState.bio) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // COMPOSABLE 1: ProfileHeader — nama dan role dari ViewModel state
        ProfileHeader(
            name = uiState.name,
            role = uiState.role
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row aksi: Follow, Pesan, Edit
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tombol Follow — toggle via ViewModel
            Button(
                onClick = { profileViewModel.toggleFollow() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if (uiState.isFollowing) Icons.Default.Check else Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = if (uiState.isFollowing) "Following" else "Follow")
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

            // Tombol Edit — masuk ke edit mode
            IconButton(
                onClick = { profileViewModel.setEditMode(!uiState.isEditMode) }
            ) {
                Icon(
                    imageVector = if (uiState.isEditMode) Icons.Default.Close else Icons.Default.Edit,
                    contentDescription = if (uiState.isEditMode) "Tutup Edit" else "Edit Profil",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ============================================================
        // FITUR EDIT PROFILE — AnimatedVisibility untuk smooth transition
        // EditProfileForm menggunakan State Hoisting:
        //   editName & editBio disimpan di ProfileScreen (parent)
        //   diteruskan ke EditProfileForm sebagai parameter
        //   callback onNameChange & onBioChange mengupdate state parent
        //   onSave memanggil ViewModel untuk persist ke UiState
        // ============================================================
        AnimatedVisibility(
            visible = uiState.isEditMode,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            EditProfileForm(
                editName = editName,
                editBio = editBio,
                onNameChange = { editName = it },          // state hoisting
                onBioChange = { editBio = it },            // state hoisting
                onSave = { profileViewModel.saveProfile(editName, editBio) },
                onCancel = { profileViewModel.setEditMode(false) }
            )
        }

        // Tampilkan Bio hanya saat BUKAN edit mode
        AnimatedVisibility(
            visible = !uiState.isEditMode,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            BioSection(bio = uiState.bio)
        }

        // ============================================================
        // FITUR DARK MODE TOGGLE
        // State isDarkMode disimpan di ViewModel
        // DarkModeToggle hanya menerima nilai dan callback (state hoisting)
        // ============================================================
        DarkModeToggle(
            isDarkMode = uiState.isDarkMode,
            onToggle = { profileViewModel.toggleDarkMode() }
        )

        // ProfileCard — Kontak
        ProfileCard(title = "Informasi Kontak") {
            InfoItem(icon = Icons.Default.Email, label = "Email", value = uiState.email)
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(icon = Icons.Default.Phone, label = "Phone", value = uiState.phone)
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(icon = Icons.Default.LocationOn, label = "Location", value = uiState.location)
        }

        // ProfileCard — Akademik
        ProfileCard(title = "Informasi Akademik") {
            InfoItem(icon = Icons.Default.Home, label = "Institusi", value = uiState.institution)
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(icon = Icons.Default.List, label = "Program Studi", value = uiState.major)
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(icon = Icons.Default.Person, label = "NIM", value = uiState.nim)
            HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 4.dp))
            InfoItem(icon = Icons.Default.DateRange, label = "Semester", value = uiState.semester)
        }

        // ProfileCard — Keahlian
        ProfileCard(title = "Keahlian") {
            SkillBadgeRow(skills = uiState.skills.take(3))
            Spacer(modifier = Modifier.height(6.dp))
            SkillBadgeRow(skills = uiState.skills.drop(3))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SkillBadgeRow(skills: List<String>) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        skills.forEach { skill -> SkillBadge(skill = skill) }
    }
}

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
