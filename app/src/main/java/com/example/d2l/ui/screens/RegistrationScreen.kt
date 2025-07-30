package com.example.d2l.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

const val SIGNUP_TAG = "SignUpScreen"

@Composable
fun RegistrationScreen(
    onSignUpSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,

) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val auth: FirebaseAuth = Firebase.auth

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    fun validateFields(): Boolean {
        emailError = if (email.isBlank()) "Email cannot be empty" else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Invalid email format" else null
        passwordError = if (password.isBlank()) "Password cannot be empty" else if (password.length < 6) "Password must be at least 6 characters" else null
        confirmPasswordError = if (confirmPassword.isBlank()) "Please confirm your password" else if (password != confirmPassword) "Passwords do not match" else null

        return emailError == null && passwordError == null && confirmPasswordError == null
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 42.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)

    ) {
        val screenWidth = LocalWindowInfo.current.containerSize.width
        val dynamicPadding = if (screenWidth < 1079) 60.dp else 150.dp
        Spacer(Modifier.height(dynamicPadding))


        Spacer(modifier = Modifier.height(24.dp))
        Text("Sign Up", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = null },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailError != null,
            supportingText = { if (emailError != null) Text(emailError!!) }
        )



        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = null; confirmPasswordError = null },
            label = { Text("Password") },
            placeholder = { Text("Minimum 6 characters") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            isError = passwordError != null,
            supportingText = { if (confirmPasswordError != null) Text(confirmPasswordError!!) }
        )



        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it; confirmPasswordError = null },
            label = { Text("Confirm Password") },
            placeholder = { Text("Re-enter your password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = confirmPasswordError != null,
            supportingText = { if (confirmPasswordError != null) Text(confirmPasswordError!!) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (validateFields()) {
                        isLoading = true
                        scope.launch {
                            try {
                                Log.d(SIGNUP_TAG, "Attempting to create user: $email")
                                auth.createUserWithEmailAndPassword(email, password).await()
                                Log.d(SIGNUP_TAG, "User creation successful for: $email")
                                Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                onSignUpSuccess()
                            } catch (e: Exception) {
                                Log.w(SIGNUP_TAG, "User creation failed for: $email", e)
                                val errorMessage = when (e) {
                                    is FirebaseAuthWeakPasswordException -> "Password is too weak. Please choose a stronger password."
                                    is FirebaseAuthUserCollisionException -> "This email address is already in use by another account."
                                    else -> "Sign up failed: ${e.localizedMessage ?: "Unknown error"}"
                                }
                                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                            } finally {
                                isLoading = false
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006FFD))
            ) {
                Text("Sign Up", color = Color.White)
            }
        }



        TextButton(onClick = {
            if (!isLoading) {
                onNavigateToLogin() } },
            modifier = Modifier.align(Alignment.Start),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF006FFD),
                containerColor = Color.Transparent),) {
            Text(fontWeight = FontWeight.SemiBold, text = "Already have an account? Login")
        }
    }
}


