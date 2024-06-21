package com.example.demo.infrastructure.service

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Converter
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "mechanic_service")
data class MechanicServiceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "service_name", nullable = false)
    val serviceName: String,

    @Column(name = "status", nullable = false)
    var status: String,

    @Column(name = "service_cost", nullable = false)
    val serviceCost: BigDecimal,

    @Column(name = "appointment_date", nullable = false)
    val appointmentDate: LocalDateTime,

    @Column(name = "partner_name", nullable = false)
    val partnerName: String,

    @Column(name = "client_name", nullable = false)
    val clientName: String,

    @Convert(converter = StringListConverter::class)
    @Column(name = "used_parts", nullable = false)
    val usedParts: List<String>,

    @Column(name = "created_date", nullable = false)
    val createdDate: LocalDateTime = LocalDateTime.now()
)

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(attribute: List<String>?): String {
        return attribute?.joinToString(",") ?: ""
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return dbData?.split(",")?.map { it.trim() } ?: emptyList()
    }
}