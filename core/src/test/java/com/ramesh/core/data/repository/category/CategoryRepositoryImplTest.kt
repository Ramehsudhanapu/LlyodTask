package com.ramesh.core.data.repository.category
import com.ramesh.core.data.datasource.remote.ApiServices
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.respository.ProductRepositoryImpl
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
class CategoryRepositoryImplTest {

    @Mock
    private lateinit var apiServices: ApiServices
    private lateinit var categoryRepositoryImpl: ProductRepositoryImpl

    @Before
    fun setUp() {
        categoryRepositoryImpl = ProductRepositoryImpl(apiServices)
    }

    @Test
    fun `getSubCategoryByProductIDApiCall should return error flow when http exception occur`() =
        runTest {
            // Given
            val expectedExceptionMessage = "Unable to fetch product. Please try again later."
            val productId = 1
            val errorBody = "".toResponseBody("application/json".toMediaTypeOrNull())
            val errorResponse = Response.error<ProductResponse>(404, errorBody)
            val httpException = HttpException(errorResponse)
            given(apiServices.getProductById(productId)).willThrow(httpException)

            // When
            val actualResult = runCatching {
                categoryRepositoryImpl.getSubCategoryByProductIDApiCall(productId).first()
            }

            // Then
            assertTrue(actualResult.isFailure)
            val actualException = actualResult.exceptionOrNull()
            assertTrue(actualException is Exception)
            Assert.assertEquals(expectedExceptionMessage, actualException?.message)
        }
}

