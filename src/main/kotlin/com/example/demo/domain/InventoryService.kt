// src/main/kotlin/com/example/demo/domain/InventoryService.kt
package com.example.demo.domain

import com.example.demo.infrastructure.storage.InventoryEntity
import com.example.demo.infrastructure.storage.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(private val inventoryRepository: InventoryRepository) {

    fun getAllStorage(): List<StorageDTO> = inventoryRepository.findAll().map { it.toDTO() }

    fun addStorageItem(item: StorageDTO): StorageDTO {
        val a = item.toEntityDTO()
        inventoryRepository.save(a)
        return item.toEntityDTO().toDTO()
    }

    private fun InventoryEntity.toDTO() = StorageDTO(
        id = this.id,
        name = this.name,
        type = this.type,
        stock = this.stock,
        price = this.price
    )

    private fun StorageDTO.toEntityDTO() = InventoryEntity(
        id = getAllStorage().size.toLong().plus(1),
        name = this.name,
        type = this.type,
        stock = this.stock,
        price = this.price
    )
}

data class StorageDTO(
    val id: Long,
    val name: String,
    val type: String,
    val stock: Int,
    val price: Int
)
