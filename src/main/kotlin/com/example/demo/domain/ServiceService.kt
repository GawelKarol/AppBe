package com.example.demo.domain

import com.example.demo.infrastructure.service.ServiceEntity
import com.example.demo.infrastructure.service.ServiceRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServiceService(private val serviceRepository: ServiceRepository) {

    fun getAllServices(): List<ServiceDTO> = serviceRepository.findAll().sortedBy { it.serviceDate }.map { it.toDTO() }

    private fun ServiceEntity.toDTO() = ServiceDTO(
        id = this.id,
        serviceDate = this.serviceDate,
        userName = this.userName,
        serviceName = this.serviceName,
        price = this.price,
        status = this.status,
        createdDate = this.createdDate
    )
}

data class ServiceDTO(
    val id: Long,
    val serviceDate: LocalDateTime,
    val userName: String,
    val serviceName: String,
    val price: Double,
    val status: String,
    val createdDate: LocalDateTime
)