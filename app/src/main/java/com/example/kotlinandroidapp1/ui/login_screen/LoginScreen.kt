package com.example.kotlinandroidapp1.ui.login_screen

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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinandroidapp1.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    onMainLayoutClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var shouldNavigate by remember { mutableStateOf(false) }

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

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

    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            delay(2000)
            isLoading = false
            onMainLayoutClick()
        }
    }

    val gradientColors = listOf(
        Color(0xFF6A11CB),
        Color(0xFF2575FC),
        Color(0xFF00B4DB)
    )

    val animatedGradient = Brush.linearGradient(
        colors = gradientColors,
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(
            x = 1000f * animatedProgress.value,
            y = 1000f * animatedProgress.value
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedGradient)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_background_pattern),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
        )

        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(1000)) +
                    slideInVertically(animationSpec = tween(1000)) { it / 2 },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Surface(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .shadow(24.dp, RoundedCornerShape(20.dp)),
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

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Selamat Datang",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2D3748)
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        label = {
                            Text(
                                text = "Email",
                                color = Color(0xFF4A5568)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(emailFocusRequester),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF6A11CB),
                            unfocusedBorderColor = Color(0xFFCBD5E0),
                            cursorColor = Color(0xFF6A11CB)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = ""
                        },
                        label = {
                            Text(
                                text = "Password",
                                color = Color(0xFF4A5568)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(passwordFocusRequester),
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF6A11CB),
                            unfocusedBorderColor = Color(0xFFCBD5E0),
                            cursorColor = Color(0xFF6A11CB)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) {
                                        Icons.Filled.AccountCircle
                                    } else {
                                        Icons.Filled.AccountBox
                                    },
                                    contentDescription = if (passwordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    },
                                    tint = Color(0xFF6A11CB)
                                )
                            }
                        },
                        singleLine = true
                    )

                    AnimatedVisibility(
                        visible = errorMessage.isNotEmpty(),
                        enter = fadeIn(animationSpec = tween(300)),
                        exit = fadeOut(animationSpec = tween(300))
                    ) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Start
                        )
                    }

                    Text(
                        text = "Lupa Password?",
                        color = Color(0xFF6A11CB),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {  },
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (email.isBlank() || password.isBlank()) {
                                errorMessage = "Email dan password harus diisi"
                            } else {
                                isLoading = true
                                shouldNavigate = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .shadow(8.dp, RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6A11CB),
                            contentColor = Color.White
                        ),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Text(
                                text = "Masuk",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(
                            modifier = Modifier.weight(1f),
                            color = Color(0xFFE2E8F0)
                        )
                        Text(
                            text = "atau",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Color(0xFF718096)
                        )
                        Divider(
                            modifier = Modifier.weight(1f),
                            color = Color(0xFFE2E8F0)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { /* Handle Google login */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF4A5568)
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 1.dp,
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Logo",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Lanjutkan dengan Google",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Belum punya akun? ",
                            color = Color(0xFF718096)
                        )
                        Text(
                            text = "Daftar",
                            color = Color(0xFF6A11CB),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {  }
                        )
                    }
                }
            }
        }
    }
}

fun Modifier.alpha(alpha: Float): Modifier = this.then(
    graphicsLayer(alpha = alpha)
)