package com.kl3jvi.medicine.repository

import com.kl3jvi.medicine.data.local.MedicineDao
import com.kl3jvi.medicine.data.local.entities.MedicineEntity
import com.kl3jvi.medicine.data.remote.MedicineApiClient
import com.kl3jvi.medicine.data.remote.models.AssociatedDrug
import com.kl3jvi.medicine.data.remote.models.HealthDataResponse
import com.kl3jvi.medicine.data.remote.models.HealthProblem
import com.kl3jvi.medicine.data.repositories.MedicineRepositoryImpl
import com.kl3jvi.medicine.domain.repositories.MedicineRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MedicineRepositoryImplTest {

    private lateinit var repository: MedicineRepository
    private val mockDao: MedicineDao = mockk()
    private val mockApiClient: MedicineApiClient = mockk()

    @Before
    fun setup() {
        repository = MedicineRepositoryImpl(mockDao, mockApiClient)
    }

    @Test
    fun `getHealthData returns correct data`() = runBlocking {
        // Arrange
        val testEntities = listOf(
            MedicineEntity(
                name = "Test Medicine 1",
                dose = "500 mg",
                strength = "100 mg",
                problemName = "Test Problem"
            ),
            MedicineEntity(
                name = "Test Medicine 2",
                dose = "1000 mg",
                strength = "200 mg",
                problemName = "Test Problem"
            )
        )
        val testApiResponse = HealthDataResponse(
            problems = mapOf(
                "Test Problem" to listOf(
                    HealthProblem(
                        medications = listOf(
                            AssociatedDrug(
                                name = "Test Medicine 1",
                                dose = "500 mg",
                                strength = "100 mg"
                            ),
                            AssociatedDrug(
                                name = "Test Medicine 2",
                                dose = "1000 mg",
                                strength = "200 mg"
                            )
                        ),
                        labs = emptyList()
                    )
                )
            )
        )
        coEvery { mockDao.getMedicines() } returns flowOf(testEntities)
        coEvery { mockApiClient.getHealthData() } returns testApiResponse
        coEvery { mockDao.insertMedicines(any()) } returns Unit

        // Act
        val result = repository.getHealthData()
            .toList()
            .flatMap{
                it.values
            }.flatten()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Test Medicine 1", result[0].name)
        assertEquals("Test Medicine 2", result[1].name)
    }

    @Test
    fun `getHealthDataByName returns correct data`() = runTest {
        // Arrange
        val testName = "Test Medicine 1"
        val testEntity = MedicineEntity(
            name = testName,
            dose = "500 mg",
            strength = "100 mg",
            problemName = "Test Problem"
        )
        coEvery { mockDao.getMedicineByName(testName) } returns flowOf(testEntity)

        // Act
        val result = repository.getHealthDataByName(testName).toList().first()

        // Assert
        assertEquals(testName, result.name)
    }

    @Test
    fun `getHealthDataByName returns null if no data found`() = runTest {
        // Arrange
        val testName = "Test Medicine 1"
        coEvery { mockDao.getMedicineByName(testName) } returns flowOf()

        // Act
        val result = repository.getHealthDataByName(testName).toList().firstOrNull()

        // Assert
        assertEquals(null, result)
    }

}