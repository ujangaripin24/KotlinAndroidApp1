package com.example.kotlinandroidapp1.ui.term_policy_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinandroidapp1.R
import com.example.kotlinandroidapp1.data.UserPreferences
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

data class TermSection(
    val title: String,
    val content: String,
    var isExpanded: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun TermPolicyScreen(
    onBoardingClick: () -> Unit,
    userPreferences: UserPreferences,
) {
    var acceptedTerms by remember { mutableStateOf(false) }
    var acceptedPrivacy by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = SpringSpec(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    val gradientColors = listOf(
        Color(0xFF4776E6),
        Color(0xFF8E54E9),
        Color(0xFF4776E6)
    )

    val animatedGradient = Brush.linearGradient(
        colors = gradientColors,
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(
            x = 1000f * animatedProgress.value,
            y = 1000f * animatedProgress.value
        )
    )

    val termsSections = remember {
        mutableStateOf(listOf(
            TermSection(
                "1. Persetujuan Penggunaan",
                "Dengan mengakses atau menggunakan aplikasi ini, Anda setuju untuk terikat oleh syarat dan ketentuan ini. Jika Anda tidak setuju dengan syarat dan ketentuan, jangan gunakan aplikasi kami."
            ),
            TermSection(
                "2. Akun Pengguna",
                "Anda bertanggung jawab untuk menjaga kerahasiaan informasi akun dan kata sandi Anda. Anda setuju untuk menerima tanggung jawab atas semua aktivitas yang terjadi di bawah akun Anda."
            ),
            TermSection(
                "3. Hak Kekayaan Intelektual",
                "Seluruh konten, fitur, dan fungsi yang ada dalam aplikasi ini, termasuk tetapi tidak terbatas pada semua informasi, perangkat lunak, teks, tampilan, gambar, video, dan audio, adalah milik kami atau pemberi lisensi kami."
            ),
            TermSection(
                "4. Pembatasan Penggunaan",
                "Anda tidak boleh menggunakan aplikasi ini untuk tujuan yang melanggar hukum, mengganggu, atau membahayakan orang lain. Anda juga tidak boleh mencoba mendapatkan akses tidak sah ke sistem atau jaringan kami."
            ),
            TermSection(
                "5. Perubahan Ketentuan",
                "Kami dapat merevisi syarat dan ketentuan ini kapan saja dengan memposting versi yang diperbarui. Dengan terus menggunakan aplikasi setelah perubahan, Anda menerima syarat dan ketentuan yang direvisi."
            ),
            TermSection(
                "6. Kebijakan Privasi",
                "Kami menghargai privasi Anda dan berkomitmen untuk melindungi informasi pribadi Anda. Kebijakan privasi kami menjelaskan bagaimana kami mengumpulkan, menggunakan, dan melindungi informasi Anda."
            )
        ))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedGradient)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Syarat & Ketentuan",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(1000)) +
                            slideInVertically(animationSpec = tween(1000)) { it / 2 },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .shadow(16.dp, RoundedCornerShape(20.dp)),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.95f)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AnimatedVisibility(
                                visible = true,
                                enter = scaleIn(animationSpec = tween(800))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = "App Logo",
                                    modifier = Modifier.size(80.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Syarat, Ketentuan, dan Kebijakan Privasi",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2D3748)
                                ),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Silakan baca dengan seksama sebelum menggunakan aplikasi kami",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color(0xFF718096)
                                ),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            termsSections.value.forEachIndexed { index, section ->
                                TermSectionItem(
                                    section = section,
                                    onExpandedChange = { expanded ->
                                        val updatedSections = termsSections.value.toMutableList()
                                        updatedSections[index] = updatedSections[index].copy(isExpanded = expanded)
                                        termsSections.value = updatedSections
                                    }
                                )
                                if (index < termsSections.value.size - 1) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { acceptedTerms = !acceptedTerms },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = acceptedTerms,
                                    onCheckedChange = { acceptedTerms = it }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Saya telah membaca dan menyetujui Syarat & Ketentuan",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { acceptedPrivacy = !acceptedPrivacy },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = acceptedPrivacy,
                                    onCheckedChange = { acceptedPrivacy = it }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Saya telah membaca dan menyetujui Kebijakan Privasi",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = {
                                    scope.launch {
                                        userPreferences.setTermAndPolicyCompleted(true)
                                        onBoardingClick()
                                        println("[term and policy] button Terima & Lanjutkan: " +userPreferences)
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .shadow(8.dp, RoundedCornerShape(12.dp)),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (acceptedTerms && acceptedPrivacy) {
                                        Color(0xFF4776E6)
                                    } else {
                                        Color(0xFFCBD5E0)
                                    },
                                    contentColor = Color.White
                                ),
                                enabled = acceptedTerms && acceptedPrivacy
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Terima",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Terima & Lanjutkan",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun TermSectionItem(
    section: TermSection,
    onExpandedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onExpandedChange(!section.isExpanded) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7FAFC)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = section.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2D3748)
                    ),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { onExpandedChange(!section.isExpanded) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (section.isExpanded) {
                            Icons.Default.Home
                        } else {
                            Icons.Default.Check
                        },
                        contentDescription = if (section.isExpanded) {
                            "Sembunyikan"
                        } else {
                            "Tampilkan"
                        },
                        tint = Color(0xFF718096)
                    )
                }
            }

            AnimatedVisibility(
                visible = section.isExpanded,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(
                    text = section.content,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF4A5568)
                    ),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

fun Modifier.alpha(alpha: Float): Modifier = this.then(
    graphicsLayer(alpha = alpha)
)