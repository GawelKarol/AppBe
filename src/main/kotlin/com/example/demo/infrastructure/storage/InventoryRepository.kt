// src/main/kotlin/com/example/demo/infrastructure/InventoryRepository.kt
package com.example.demo.infrastructure.storage

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : JpaRepository<InventoryEntity, Long> {
    fun findByName(name: String): InventoryEntity?
}
