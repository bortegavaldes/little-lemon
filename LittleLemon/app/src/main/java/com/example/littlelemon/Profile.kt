package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
    val firstName = sharedPreferences.getString("first_name", "") ?: ""
    val lastName = sharedPreferences.getString("last_name", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""
    Column(modifier = Modifier.fillMaxWidth()) {
        Row (modifier = Modifier.fillMaxWidth().padding(top = 15.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = { navController.navigate(Home.route) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.inverseOnSurface,
                    contentDescription = "Back Button",
                )
            }
            //TopHeader(Modifier.fillMaxWidth().padding(top = 30.dp))
            TopHeader(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            Text(
                text = "Profile Information",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                readOnly = true,
                enabled = false,
                onValueChange = {},
                label = {
                    Text("First Name")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface
                )
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName,
                readOnly = true,
                enabled = false,
                onValueChange = {},
                label = {
                    Text("Last Name")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface
                )
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                readOnly = true,
                enabled = false,
                onValueChange = {},
                label = {
                    Text("Email")
                },
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth().height(height = 50.dp),
                onClick = {
                    sharedPreferences.edit().clear().apply()
                    Toast.makeText(context, "Logged out successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Onboarding.route) {
                      popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface,
                )
            ){
                Text(text = "Log out", style = MaterialTheme.typography.titleLarge)
            }

        }
    }
}

@Composable
@Preview
fun ProfilePreview(){
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}