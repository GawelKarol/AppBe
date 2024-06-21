// src/main/kotlin/com/example/demo/domain/DocumentService.kt
package com.example.demo.domain

import com.example.demo.infrastructure.document.DocumentEntity
import com.example.demo.infrastructure.document.DocumentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DocumentService(private val documentRepository: DocumentRepository) {

    fun getAllDocuments(): List<DocumentDTO> = documentRepository.findAll().sortedBy { it.date }.map { it.toDTO() }

    fun getDocumentsByName(name: String): List<DocumentDTO> {
        return documentRepository.findByName(name).sortedBy { it.date }.map { it.toDTO() }
    }

    fun getDocumentsByPartnerName(partnerName: String): List<DocumentDTO> {
        return documentRepository.findByPartnerName(partnerName).sortedBy { it.date }.map { it.toDTO() }
    }

    private fun DocumentEntity.toDTO() = DocumentDTO(
        id = this.id,
        number = this.number,
        user = this.name,
        partnerName = this.partnerName,
        type = this.type,
        date = this.date
    )
}

data class DocumentDTO(
    val id: Long,
    val number: String,
    val user: String,
    val partnerName: String,
    val type: String,
    val date: LocalDate
)