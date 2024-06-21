// src/main/kotlin/com/example/demo/domain/InventoryEntity.kt
package com.example.demo.infrastructure.storage

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "inventory")
data class InventoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "type", nullable = false)
    val type: String,

    @Column(name = "stock", nullable = false)
    val stock: Int,

    @Column(name = "price", nullable = false)
    val price: Int
)
