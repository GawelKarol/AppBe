// src/main/kotlin/com/example/demo/infrastructure/UserRepository.kt
package com.example.demo.infrastructure.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>{
    fun findByEmail(email: String): UserEntity?
}
