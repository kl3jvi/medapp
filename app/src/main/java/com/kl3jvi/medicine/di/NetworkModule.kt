package com.kl3jvi.medicine.di

import com.kl3jvi.medicine.data.remote.MedicineApiClient
import com.kl3jvi.medicine.data.remote.MedicineApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = "https://run.mocky.io/"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideMedicineApiService(retrofit: Retrofit): MedicineApiService =
        retrofit.create(MedicineApiService::class.java)

    @Provides
    @Singleton
    fun provideMedicineApiClient(medicineApiService: MedicineApiService) =
        MedicineApiClient(medicineApiService)

}