package com.example.demo.infrastructure.service

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "services")
data class ServiceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "service_date", nullable = false)
    val serviceDate: LocalDateTime,

    @Column(name = "user_name", nullable = false)
    val userName: String,

    @Column(name = "service_name", nullable = false)
    val serviceName: String,

    @Column(name = "price", nullable = false)
    val price: Double,

    @Column(name = "status", nullable = false)
    val status: String,

    @Column(name = "created_date", nullable = false)
    val createdDate: LocalDateTime
)
