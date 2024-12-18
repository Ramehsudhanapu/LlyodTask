package com.ramesh.assessment.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramesh.core.R
import com.ramesh.assessment.component.ProgressProduct
import com.ramesh.assessment.home.section.HomeContent
import com.ramesh.core.data.UiState
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.ui.componets.particle.SearchBar
import com.ramesh.core.ui.componets.particle.template.MainTemplate
import com.ramesh.core.ui.componets.particle.theme.Gray200
import com.ramesh.core.ui.componets.particle.theme.md_theme_dark_inverseSurface

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit
) {

    val UiStateCategory by remember { viewModel.UiStateCategory }.collectAsState()

    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = modifier.background(MaterialTheme.colorScheme.primary),
                isEnabled = false,
                onSearchClicked = { navigateToSearch() },


                )

        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize().background(Gray200)
            )
            {
                when (UiStateCategory) {
                    is UiState.Loading -> {
                        viewModel.getCategoryApiCall()
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        HomeContent(
                            modifier = modifier,
                            listProduct = (UiStateCategory as UiState.Success<ProductResponse>).data.products,
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

        })
}
















