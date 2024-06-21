// src/main/kotlin/com/example/demo/domain/DocumentService.kt
package com.example.demo.domain

import com.example.demo.infrastructure.document.DocumentEntity
import com.example.demo.infrastructure.document.DocumentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DocumentService(private val documentRepository: DocumentRepository) {

    fun getAllDocuments(): List<DocumentDTO> = documentRepository.findAll().sortedBy { it.date }.map { it.toDTO() }

private fun DocumentEntity.toDTO() = DocumentDTO(
    id = this.id,
    number = this.number,
    user = this.user,
    type = this.type,
    date = this.date
)
}
data class DocumentDTO(
    val id: Long,
    val number: String,
    val user: String,
    val type: String,
    val date: LocalDate
)