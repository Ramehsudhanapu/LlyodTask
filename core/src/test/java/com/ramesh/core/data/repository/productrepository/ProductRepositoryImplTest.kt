package com.ramesh.core.data.repository.productrepository

import com.ramesh.core.data.datasource.remote.productRemoteDataSource.ProductApiService
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.respository.ProductRepositoryImpl
import com.ramesh.core.util.AppStrings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import retrofit2.HttpException
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryImplTest {

    @Mock
    private lateinit var apiServices: ProductApiService
    private lateinit var productRepositoryImpl: ProductRepositoryImpl

    @Before
    fun setUp() {
        productRepositoryImpl = ProductRepositoryImpl(apiServices)
    }

    @Test
    fun `getSubCategoryByProductIDApiCall should return error flow when http exception occur`() =
        runTest {
            // Given
            val expectedExceptionMessage = AppStrings.UNABLE_TO_FETCH_PRODUCTS
            val productId = 1
            val errorBody = "".toResponseBody("application/json".toMediaTypeOrNull())
            val errorResponse = Response.error<ProductResponse>(404, errorBody)
            val httpException = HttpException(errorResponse)
            given(apiServices.getProductById(productId)).willThrow(httpException)

            // When
            val actualResult = runCatching {
                productRepositoryImpl.getSubCategoryByProductIDApiCall(productId).first()
            }

            // Then
            assertTrue(actualResult.isFailure)
            val actualException = actualResult.exceptionOrNull()
            assertTrue(actualException is Exception)
            Assert.assertEquals(expectedExceptionMessage, actualException?.message)
        }
}

