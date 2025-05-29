package com.example.foodzy.di

import com.example.foodzy.data.datasource.SepetDataSource
import com.example.foodzy.data.datasource.YemeklerDataSource
import com.example.foodzy.data.repo.SepetRepository
import com.example.foodzy.data.repo.YemeklerRepository
import com.example.foodzy.retrofit.ApiUtils
import com.example.foodzy.retrofit.SepetDao
import com.example.foodzy.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //yemek
    @Provides
    @Singleton
    fun provideYemeklerRepository(yemeklerDataSource : YemeklerDataSource) : YemeklerRepository{
        return YemeklerRepository(yemeklerDataSource)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(yemeklerDao : YemeklerDao) : YemeklerDataSource{
        return YemeklerDataSource(yemeklerDao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }

    //sepet
    @Provides
    @Singleton
    fun provideSepetRepository(sepetDataSporce : SepetDataSource) : SepetRepository{
        return SepetRepository(sepetDataSporce)
    }

    @Provides
    @Singleton
    fun provideSepetDataSource(sepetDao: SepetDao) : SepetDataSource{
        return SepetDataSource(sepetDao)
    }

    @Provides
    @Singleton
    fun provideSepetDao() : SepetDao{
        return ApiUtils.getSepetDao()
    }

}