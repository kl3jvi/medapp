package com.kl3jvi.medicine.di

import android.app.Application
import androidx.room.Room
import com.kl3jvi.medicine.data.local.MedicineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): MedicineDatabase {
        return Room
            .databaseBuilder(application, MedicineDatabase::class.java, "Medicine.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMedicineDao(medicineDatabase: MedicineDatabase) = medicineDatabase.medicineDao()
}