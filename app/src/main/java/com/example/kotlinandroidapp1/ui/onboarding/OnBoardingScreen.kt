package com.example.kotlinandroidapp1.ui.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinandroidapp1.R
import kotlinx.coroutines.launch

// Data class untuk halaman onboarding
data class OnboardingPage(
    val image: Int,
    val title: String,
    val description: String,
    val gradientColors: List<Color>
)

// Daftar halaman onboarding
val onboardingPages = listOf(
    OnboardingPage(
        image = R.drawable.ic_onboarding_1,
        title = "Jelajahi Dunia",
        description = "Temukan tempat-tempat menarik di sekitar Anda dengan peta interaktif",
        gradientColors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    ),
    OnboardingPage(
        image = R.drawable.ic_onboarding_2,
        title = "Rute Terbaik",
        description = "Dapatkan panduan rute tercepat dan teraman ke tujuan Anda",
        gradientColors = listOf(Color(0xFF11998E), Color(0xFF38EF7D))
    ),
    OnboardingPage(
        image = R.drawable.ic_onboarding_3,
        title = "Mulai Petualangan",
        description = "Siap untuk menjelajah? Ayo mulai perjalanan Anda sekarang!",
        gradientColors = listOf(Color(0xFFFF416C), Color(0xFFFF4B2B))
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onLoginClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = onboardingPages[pagerState.currentPage].gradientColors
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPage(page = onboardingPages[page])
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            PageIndicator(
                pageCount = onboardingPages.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            AnimatedVisibility(
                visible = pagerState.currentPage == onboardingPages.size - 1,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                exit = fadeOut(animationSpec = tween(durationMillis = 500))
            ) {
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .shadow(16.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = onboardingPages[pagerState.currentPage].gradientColors[0]
                    )
                ) {
                    Text(
                        text = "Continue",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            AnimatedVisibility(
                visible = pagerState.currentPage != onboardingPages.size - 1,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                exit = fadeOut(animationSpec = tween(durationMillis = 500))
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(onboardingPages.size - 1)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Skip",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingPage(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                initialOffsetX = { it * 2 }
            ) + fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = slideOutHorizontally(
                animationSpec = tween(durationMillis = 500),
                targetOffsetX = { -it }
            ) + fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Image(
                painter = painterResource(id = page.image),
                contentDescription = page.title,
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000, delayMillis = 300))
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000, delayMillis = 600))
        ) {
            Text(
                text = page.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White.copy(alpha = 0.9f)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(pageCount) { page ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(
                        if (page == currentPage) Color.White
                        else Color.White.copy(alpha = 0.5f)
                    )
            )
        }
    }
}