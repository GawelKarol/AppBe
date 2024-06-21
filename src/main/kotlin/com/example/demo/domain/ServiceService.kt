package com.example.demo.domain

import com.example.demo.infrastructure.MechanicServiceUpdateRequest
import com.example.demo.infrastructure.document.DocumentEntity
import com.example.demo.infrastructure.document.DocumentRepository
import com.example.demo.infrastructure.service.MechanicServiceEntity
import com.example.demo.infrastructure.service.MechanicServiceRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.absoluteValue
import kotlin.random.Random

@Service
class ServiceService(
    private val mechanicServiceRepository: MechanicServiceRepository,
    private val documentRepository: DocumentRepository,
    private val inventoryService: InventoryService
) {
    private val mechanicServices = mutableListOf(
        MechanicService(
            1,
            "Wymiana oleju",
            29.99,
            "2024-07-01",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Olej", "Filtr")
        ),
        MechanicService(
            2,
            "Wymiana opon",
            19.99,
            "2024-07-02",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Opona")
        ),
        MechanicService(
            3,
            "Inspekcja hamulców",
            49.99,
            "2024-07-03",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Klocki hamulcowe")
        ),
        MechanicService(
            4,
            "Regulacja silnika",
            99.99,
            "2024-07-04",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Świece zapłonowe", "Filtr powietrza")
        ),
        MechanicService(
            5,
            "Wymiana akumulatora",
            89.99,
            "2024-07-05",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Akumulator")
        ),
        MechanicService(
            6,
            "Kontrola skrzyni biegów",
            129.99,
            "2024-07-06",
            listOf("Wylezalek", "AutoParts", "RondoMechanics"),
            listOf("Płyn do skrzyni biegów")
        )
    )

    fun getAllMechanicServices(): List<MechanicServiceEntity> {
        return mechanicServiceRepository.findAll()
    }

    fun getServicesByClientName(clientName: String): List<MechanicServiceEntity> {
        return mechanicServiceRepository.findByClientName(clientName)
    }

    fun getServicesByPartnerName(partnerName: String): List<MechanicServiceEntity> {
        return mechanicServiceRepository.findByPartnerName(partnerName)
    }

    fun getAllMechanicServicesList(): List<MechanicServiceDTO> {
        return mechanicServices.map { it.toDTO() }
    }

    @Transactional
    fun changePartnerStatus(serviceId: Long): MechanicServiceEntity {
        val service = mechanicServiceRepository.findById(serviceId)
            .orElseThrow { IllegalArgumentException("Service with id $serviceId not found") }
        if (service.status == "Otrzymaliśmy twoje zgłoszenie") {
            service.status = "Twoje zgłoszenie zostało potwierdzone"
        }else if (service.status == "Twoje zgłoszenie zostało potwierdzone") {
            service.status = "Twoje auto zostało przyjęte do serwisu"
        } else if (service.status == "Twoje auto zostało przyjęte do serwisu") {
            service.status = "Twoja naprawa została zakończona"
        }
        return mechanicServiceRepository.save(service)
    }

   @Transactional
    fun changeClientStatus(serviceId: Long): MechanicServiceEntity {
        val service = mechanicServiceRepository.findById(serviceId)
            .orElseThrow { IllegalArgumentException("Service with id $serviceId not found") }

            service.status = "Twoja reklamacja została złożona"
        return mechanicServiceRepository.save(service)
    }

    @Transactional
    fun rejectPartnerStatus(serviceId: Long): MechanicServiceEntity {
        val service = mechanicServiceRepository.findById(serviceId)
            .orElseThrow { IllegalArgumentException("Service with id $serviceId not found") }

        if (service.status == "Otrzymaliśmy twoje zgłoszenie") {
            service.status = "Twoje zgłoszenie zostało odrzucone"
        }
        return mechanicServiceRepository.save(service)
    }

    fun createMechanicService(createRequest: MechanicServiceUpdateRequest) {
        val service = mechanicServices.find { it.orderId == createRequest.orderId }
            ?: throw RuntimeException("Service not found")

        val usedPartsList = service.usedParts.ifEmpty { listOf("Brak części") }

        val newEntity = MechanicServiceEntity(
            id = getAllMechanicServices().size.toLong().plus(1),
            serviceName = service.serviceName,
            status = "Otrzymaliśmy twoje zgłoszenie",
            serviceCost = service.serviceCost.toBigDecimal(),
            appointmentDate = LocalDateTime.parse("${createRequest.appointmentDate}T${createRequest.hour}"),
            partnerName = createRequest.partner,
            clientName = createRequest.fullName,
            usedParts = usedPartsList,
            createdDate = LocalDateTime.now()
        )
        mechanicServiceRepository.save(newEntity)

        val newDocumentEntity = DocumentEntity(
            number = Random.nextInt().absoluteValue.toString(),
            name = createRequest.fullName,
            partnerName = createRequest.partner,
            type = "FV",
            date = LocalDate.now()
        )
        documentRepository.save(newDocumentEntity)

        usedPartsList.map { inventoryService.decrementStockByName(it) }
    }

    private fun MechanicService.toDTO() = MechanicServiceDTO(
        orderId, serviceName, serviceCost, appointmentDate, ourPartners
    )
}

data class MechanicService(
    val orderId: Int,
    val serviceName: String,
    val serviceCost: Double,
    var appointmentDate: String,
    var ourPartners: List<String>,
    val usedParts: List<String>,
)

data class MechanicServiceDTO(
    val orderId: Int,
    val serviceName: String,
    val serviceCost: Double,
    var appointmentDate: String,
    var ourPartners: List<String>,
)