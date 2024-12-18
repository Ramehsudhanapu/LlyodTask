package com.ramesh.core.di

import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.data.respository.CategoryRepositoryImpl
import com.ramesh.core.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiServices): CategoryRepository {
        return CategoryRepositoryImpl(apiService)
    }
}