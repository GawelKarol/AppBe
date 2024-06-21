// src/main/kotlin/com/example/demo/infrastructure/DocumentRepository.kt
package com.example.demo.infrastructure.document

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : JpaRepository<DocumentEntity, Long> {
    fun findByName(name: String): List<DocumentEntity>
    fun findByPartnerName(partnerName: String): List<DocumentEntity>
}
