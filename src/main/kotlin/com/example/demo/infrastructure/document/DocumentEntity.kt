// src/main/kotlin/com/example/demo/domain/DocumentEntity.kt
package com.example.demo.infrastructure.document

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "documents")
data class DocumentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "number", nullable = false)
    val number: String,

    @Column(name = "user", nullable = false)
    val user: String,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "date", nullable = false)
    val date: LocalDate
)
