package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

enum class FoodCategory(val displayName: String) {
    STARTERS("Starters"),
    MAINS("Mains"),
    DESSERTS("Desserts"),
    DRINKS("Drinks")
}

@Composable
fun Home(navController: NavHostController, menuDao: MenuItemDao) {
    var categorySelected by remember { mutableStateOf<FoodCategory?>(null) }
    var searchPhrase by remember { mutableStateOf("") }
    val databaseMenuItems by menuDao.getAll().observeAsState(initial = emptyList())
    var filteredList = remember(categorySelected, databaseMenuItems) {
        if (categorySelected == null) databaseMenuItems
        else databaseMenuItems.filter { it.category.equals(categorySelected!!.displayName, ignoreCase = true
        ) }
    }
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
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "")},
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text="ORDER FOR DELIVERY!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
            fontWeight = FontWeight.ExtraBold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)){
            items(FoodCategory.entries){
                category ->
                FilterChip(
                    selected = categorySelected == category,
                    onClick = {
                        categorySelected = if (categorySelected == category) null else category
                    },
                    label = { Text(category.displayName) },
                    shape = RoundedCornerShape(50),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedLabelColor = MaterialTheme.colorScheme.onSurface,
                        selectedContainerColor = MaterialTheme.colorScheme.inverseOnSurface, // Gris claro al seleccionar
                        labelColor = MaterialTheme.colorScheme.inverseOnSurface,
                        containerColor = MaterialTheme.colorScheme.onSurface       // Gris muy tenue por defecto
                    ),
                    border = null
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 20.dp),
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.onSurface
        )
        if ( ! searchPhrase.trim().isEmpty()) {
            filteredList = filteredList.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }
        }
        MenuItems(filteredList)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuList: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(items = menuList) { _, item ->
            Row(
                modifier = Modifier.padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(
                            bottom = 4.dp)
                        )

                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                        )

                    Text(
                        text = "$" + "%.2f".format(item.price),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = 4.dp)
                        )
                }
                GlideImage(
                    modifier = Modifier
                        .size(70.dp),
                    model = item.image,
                    contentDescription = "Image of ${item.title}",
                    contentScale = ContentScale.Crop
                )
            }
            HorizontalDivider(
                //modifier = Modifier.padding(horizontal = 20.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
@Preview
fun HomePreview() {
    LittleLemonTheme {
        Home(rememberNavController(), {} as MenuItemDao)
    }
}
