# My Profile App
Aplikasi Android menampilkan halaman profil pengguna menggunakan Kotlin dan Jetpack Compose.

## Cara Menjalankan
1. Clone repository ini
2. Buka project menggunakan **Android Studio**
3. Pastikan sudah menginstall SDK Android (min SDK 24)
4. Jalankan aplikasi di emulator atau perangkat fisik via tombol **Run**

## Fitur

### 1. ProfileHeader — Header dengan Foto Profil Circular dan Nama
`ProfileHeader` menampilkan avatar berbentuk lingkaran menggunakan kombinasi `Box`, `clip(CircleShape)`, dan `Icon` sebagai placeholder foto profil, disertai nama dan role pengguna.

```kotlin
@Composable
fun ProfileHeader(name: String, role: String) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(3.dp, Color.White, CircleShape)
        ) {
            Icon(imageVector = Icons.Default.Person, ...)
        }
        Text(text = name, fontWeight = FontWeight.Bold, color = Color.White)
        Text(text = role, fontStyle = FontStyle.Italic, color = Color.White.copy(alpha = 0.85f))
    }
}
```

### 2. InfoItem — List Informasi: Email, Phone, Location
`InfoItem` adalah komponen reusable yang menampilkan satu baris informasi terdiri dari `Icon`, label, dan nilai. Digunakan berulang untuk Email, Phone, Location, dan data akademik.

```kotlin
@Composable
fun InfoItem(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, tint = MaterialTheme.colorScheme.primary)
        Column {
            Text(text = label, fontSize = 12.sp, color = Color.Gray)
            Text(text = value, fontSize = 15.sp)
        }
    }
}
```

### 3. ProfileCard — Card Container Reusable dengan Slot Konten
`ProfileCard` menggunakan pola **slot API** — menerima lambda `content` sebagai parameter sehingga kontennya fleksibel dan dapat diisi apa saja. Dipakai untuk card Kontak, Akademik, dan Keahlian.

```kotlin
@Composable
fun ProfileCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit  // slot pattern
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            HorizontalDivider()
            content()  // konten diisi oleh caller
        }
    }
}
```

### 4. BioSection — Bio/Deskripsi Singkat
`BioSection` menampilkan deskripsi singkat pengguna di dalam `Card` dengan warna `secondaryContainer` agar tampil berbeda dari card informasi lainnya.

```kotlin
@Composable
fun BioSection(bio: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(text = "Tentang Saya", fontWeight = FontWeight.Bold)
        Text(text = bio, lineHeight = 22.sp)
    }
}
```

### 5. State — Tombol Follow Interaktif
Tombol **Follow** menggunakan `remember` dan `mutableStateOf` untuk menyimpan state secara lokal. Saat diklik, teks dan icon berubah antara "Follow" dan "Following".

```kotlin
var isFollowing by remember { mutableStateOf(false) }

Button(onClick = { isFollowing = !isFollowing }) {
    Icon(
        imageVector = if (isFollowing) Icons.Default.Check else Icons.Default.Add
    )
    Text(text = if (isFollowing) "Following" else "Follow")
}
```

## Struktur Project

```
com.example.profileapp/
├── ui/
│   ├── components/
│   │   └── ProfileComponents.kt   # ProfileHeader, InfoItem, ProfileCard, BioSection
│   └── screen/
│       └── ProfileScreen.kt       # Layar utama + SkillBadge
└── MainActivity.kt                # Entry point
```

## Composable Functions yang Dibuat

| # | Nama | Konsep yang Diterapkan |
|---|------|------------------------|
| 1 | `ProfileHeader` | Box, Column, clip(CircleShape), border |
| 2 | `InfoItem` | Row, Icon, Text, Modifier.padding |
| 3 | `ProfileCard` | Card, Column, Slot API (lambda content) |
| 4 | `BioSection` | Card, Row, Text, secondary color |
| 5 | `SkillBadgeRow` | Row, Arrangement.spacedBy |
| 6 | `SkillBadge` | Surface, Text, primaryContainer |

## Teknologi
- **Kotlin** + **Jetpack Compose**
- **Material 3** (tema dan komponen UI)
- **ViewModel + State** (state management dasar)

## Screenshot Aplikasi
<img width="1080" height="2400" alt="Image" src="https://github.com/user-attachments/assets/5bf18c11-c7e3-48f2-966c-ec86575752ff" />
<img width="1080" height="2400" alt="Screenshot_20260326_095438" src="https://github.com/user-attachments/assets/87c1b023-3b94-4bf4-8856-78cc62c2537f" />
<img width="1080" height="2400" alt="Screenshot_20260326_095501" src="https://github.com/user-attachments/assets/5f3b2a8d-6101-4c90-8ec1-52a9c0ed57a5" />
