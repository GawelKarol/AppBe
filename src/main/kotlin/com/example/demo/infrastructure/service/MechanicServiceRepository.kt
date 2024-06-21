package com.example.demo.infrastructure.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MechanicServiceRepository : JpaRepository<MechanicServiceEntity, Long> {
    fun findByClientName(clientName: String): List<MechanicServiceEntity>
    fun findByPartnerName(partnerName: String): List<MechanicServiceEntity>
}