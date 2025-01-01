package com.ramesh.assessment.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramesh.core.R
import com.ramesh.assessment.component.ProgressProduct
import com.ramesh.assessment.home.section.HomeContent
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.ui.componets.particle.SearchBar
import com.ramesh.core.ui.componets.particle.template.MainTemplate
import com.ramesh.core.ui.componets.particle.theme.Gray200

@Composable
fun HomeScreen(
    categoryName: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    val uiState by viewModel._uiStateCategory.collectAsState()

    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = modifier.background(MaterialTheme.colorScheme.primary),
                isEnabled = false,
                onSearchClicked = {
                    // todo
                },
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Gray200)
            ) {
                // Load data here, within the content
                LaunchedEffect(key1 = categoryName) {
                    viewModel.getAllProductsByCategory(categoryName)
                }

                when (val currentUiState = uiState) {
                    is UiState.Loading -> {
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        val products = currentUiState.data
                        HomeContent(
                            modifier = modifier,
                            listProduct = products,
                            navigateToDetail = navigateToDetail,
                        )
                    }

                    is UiState.Error -> {
                        Text(
                            text = stringResource(R.string.error_product),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }


                }
            }
        }
    )
}