package com.victorteka.data.remote.di

import FoodStatsServiceImpl
import com.google.gson.Gson
import com.victorteka.data.remote.api.NutritionixApi
import com.victorteka.data.remote.utils.AuthInterceptor
import com.victorteka.domain.services.FoodStatsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkhttpClientToken(logging: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(AuthInterceptor())
            addInterceptor(logging) //Wrap in debug
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl("https://trackapi.nutritionix.com/v2/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideFoodStatsService(nutritionixApi: NutritionixApi): FoodStatsService =
        FoodStatsServiceImpl(nutritionixApi)

    @Provides
    @Singleton
    fun provideNutritionixApi(retrofit: Retrofit): NutritionixApi = retrofit.create(NutritionixApi::class.java)
}