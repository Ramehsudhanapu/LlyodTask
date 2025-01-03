package com.ramesh.assessment.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramesh.assessment.utility.UiState

import com.ramesh.core.ui.componets.particle.theme.md_theme_light_onPrimary

@Composable
fun CategoryListScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategories()
    }

    Box(modifier = Modifier.fillMaxSize().background(md_theme_light_onPrimary), // Set background color here
    ) {
        if (state.value is UiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            when (val uiState = state.value) {
                is UiState.Success -> {
                    CategoryColumn(
                        categories = uiState.data,
                        navigateToHome = navigateToHome,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                is UiState.Error -> {
                    // Display the error message
                    Text(
                        text = uiState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Red // Change color to indicate error
                    )

                }

                else -> {
                    // Handle other states if needed, or do nothing
                }
            }
        }
    }
}

@Composable
fun CategoryColumn(
    categories: List<String>,
    navigateToHome: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(categories) { category ->
            CategoryCard(category = category, onCategoryClick = { navigateToHome(category) })
        }
    }
}

@Composable
fun CategoryCard(category: String, onCategoryClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(2.2f)
            .clickable { onCategoryClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0)) // Set a solid background color here
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = category.capitalize(Locale.current),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold

            )
        }
    }
}