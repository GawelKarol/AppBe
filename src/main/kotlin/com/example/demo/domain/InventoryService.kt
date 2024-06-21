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

    fun getStorageItemByNameList(name: List<String>): List<InventoryEntity?> {
       return name.map { inventoryRepository.findByName(it) }
    }

    fun decrementStockByName(name: String): InventoryEntity? {
        val inventory = inventoryRepository.findByName(name) ?: return null
        if (inventory.stock > 0) {
            val updatedInventory = inventory.copy(stock = inventory.stock - 1)
            return inventoryRepository.save(updatedInventory)
        }
        return null
    }

    private fun InventoryEntity.toDTO() = StorageDTO(
        id = this.id,
        name = this.name,
        stock = this.stock,
        price = this.price
    )

    private fun StorageDTO.toEntityDTO() = InventoryEntity(
        id = getAllStorage().size.toLong().plus(1),
        name = this.name,
        stock = this.stock,
        price = this.price
    )
}

data class StorageDTO(
    val id: Long,
    val name: String,
    val stock: Int,
    val price: Int
)
