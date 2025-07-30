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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.d2l.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

const val LOGIN_SCREEN_TAG = "LoginScreenLogic"
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val auth: FirebaseAuth = Firebase.auth
    val focusManager = LocalFocusManager.current
    var isLoading by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        val screenWidth = LocalWindowInfo.current.containerSize.width
        val dynamicPadding = if (screenWidth < 1079) 100.dp else 208.dp
        Spacer(Modifier.height(dynamicPadding))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
        )
        Spacer(Modifier.height(48.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            value = emailState.value,
            onValueChange = { emailState.value = it  },
            label = { Text("Email") },
            placeholder = { Text("Enter your email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

        )


        OutlinedTextField(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            placeholder = { Text("Enter your password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(Modifier.height(32.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        } else {
            Button(
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    focusManager.clearFocus()
                    val email = emailState.value
                    val password = passwordState.value

                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                        return@Button
                    }


                    isLoading = true
                    Log.d(LOGIN_SCREEN_TAG, "Attempting to sign in user: $email")

                    scope.launch {
                        try {

                            auth.signInWithEmailAndPassword(email, password).await()

                            Log.d(LOGIN_SCREEN_TAG, "SUCCESS: User $email signed in.")
                            Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                            onLoginSuccess() //

                        } catch (e: Exception) {
                            Log.e(LOGIN_SCREEN_TAG, "FAILURE: Could not sign in user $email.", e)
                            val errorMessage = when (e) {
                                is FirebaseAuthInvalidUserException -> "No account found with this email."
                                is FirebaseAuthInvalidCredentialsException -> "Incorrect password. Please try again."
                                else -> "Login failed: ${e.localizedMessage ?: "Unknown error"}"
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        } finally {
                            isLoading = false
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006FFD))
            )

            {
                Text(color = Color.White, text = "Login")
            }
        }


        TextButton(
            onClick = {
                if (!isLoading) {
                    onNavigateToSignUp()
                }
            },
            modifier = Modifier.align(Alignment.Start),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF006FFD),
                containerColor = Color.Transparent
            ),
        ) {
            Text(fontWeight = FontWeight.SemiBold, text = "Sign Up")
        }
    }
}

