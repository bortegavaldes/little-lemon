package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        TopHeader(Modifier.fillMaxWidth().padding(top = 10.dp))
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .fillMaxWidth().height(130.dp), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Let's get to know you",
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.displayMedium,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            Text(
                text = "Personal Information",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                label = {
                    Text("First Name")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                onValueChange = { lastName = it },
                label = {
                    Text("Last Name")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,      // Color del texto al escribir
                    unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,   // Color del texto si no está seleccionado
                    focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                label = {
                    Text("Email")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth().height(height = 50.dp),
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // 1. Guardar datos en SharedPreferences
                        sharedPreferences.edit().apply {
                            putString("first_name", firstName)
                            putString("last_name", lastName)
                            putString("email", email)
                            putBoolean("is_registered", true)
                            apply() // Aplicar cambios
                        }

                        // 2. MOSTRAR MENSAJE DE ÉXITO AQUÍ
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

                        // 3. Navegar a Home (y limpiar el stack)
                        navController.navigate(Home.route) {
                            // Esto evita que el usuario regrese al Onboarding al pulsar "atrás"
                            popUpTo(Onboarding.route) { inclusive = true }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface,
                )
            ){
                Text(text = "Register", style = MaterialTheme.typography.titleLarge)
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingPreview(){
    LittleLemonTheme { Onboarding(rememberNavController()) }
}