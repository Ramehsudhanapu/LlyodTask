package com.ramesh.core.di

import com.ramesh.core.data.respository.CategoryRepositoryImpl
import com.ramesh.core.data.respository.ProductRepositoryImpl
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}
