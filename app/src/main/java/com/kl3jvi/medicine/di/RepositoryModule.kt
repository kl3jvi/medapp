package com.kl3jvi.medicine.di

import com.kl3jvi.medicine.data.repositories.MedicineRepositoryImpl
import com.kl3jvi.medicine.domain.repositories.MedicineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindMedicineRepository(
        medicineRepositoryImpl: MedicineRepositoryImpl
    ): MedicineRepository
}