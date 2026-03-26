package com.example.profileapp.ui.state

data class ProfileUiState(
    val name: String = "Bayu Brigas Novaldi",
    val role: String = "Mahasiswa Teknik Informatika Itera",
    val bio: String = "Mahasiswa semester 6 yang sedang belajar pengembangan " +
            "aplikasi mobile, belajar Kotlin Multiplatform dan " +
            "Compose. Suka ngidding, ngematcha, dan main badminton.",
    val email: String = "bayu.123140072@student.itera.ac.id",
    val phone: String = "+62 857-8310-8974",
    val location: String = "Bandar Lampung, Indonesia",
    val institution: String = "Institut Teknologi Sumatera",
    val major: String = "Teknik Informatika",
    val nim: String = "123140072",
    val semester: String = "6 (Genap 2025/2026)",
    val skills: List<String> = listOf("Kotlin", "Jetpack Compose", "Android", "Git", "Firebase", "REST API"),
    val isFollowing: Boolean = false,
    val isDarkMode: Boolean = false,
    val isEditMode: Boolean = false
)
