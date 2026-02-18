package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.components.TopHeader
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Home(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row (modifier = Modifier.fillMaxWidth().padding(top = 15.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically){
            TopHeader(modifier = Modifier.weight(1f))

            IconButton(onClick = {
                navController.navigate(Profile.route)
            },
                modifier = Modifier
                    .size(60.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize().clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Go to profile"
                )
            }
        }
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.inversePrimary)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(height = 300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(weight = 0.5f)
                ) {
                    Text(
                        text = "Chicago",
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        text = stringResource(R.string.home_banner_description),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }
            var searchPhrase by remember { mutableStateOf("") }
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { newText: String ->
                    searchPhrase = newText
                },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.inverseOnSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    LittleLemonTheme {
        Home(rememberNavController())
    }
}