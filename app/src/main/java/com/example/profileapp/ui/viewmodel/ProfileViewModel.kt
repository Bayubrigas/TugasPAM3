package com.example.profileapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.profileapp.ui.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // Follow toggle
    fun toggleFollow() {
        _uiState.update { it.copy(isFollowing = !it.isFollowing) }
    }

    // Dark mode toggle
    fun toggleDarkMode() {
        _uiState.update { it.copy(isDarkMode = !it.isDarkMode) }
    }

    // Enter / exit edit mode
    fun setEditMode(enabled: Boolean) {
        _uiState.update { it.copy(isEditMode = enabled) }
    }

    // Save profile edits
    fun saveProfile(name: String, bio: String) {
        _uiState.update {
            it.copy(
                name = name.trim().ifBlank { it.name },
                bio = bio.trim().ifBlank { it.bio },
                isEditMode = false
            )
        }
    }
}
