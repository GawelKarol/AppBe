// src/main/kotlin/com/example/demo/domain/UserService.kt
package com.example.demo.domain

import com.example.demo.infrastructure.users.UserEntity
import com.example.demo.infrastructure.users.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<UserDTO> = userRepository.findAll().map { it.toDTO() }

    fun getUserById(id: Long): UserDTO = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }.toDTO()

    fun createUser(userDTO: UserDTO) : UserDTO {
        userRepository.save(userDTO.toEntityDTO())
        return userDTO.toEntityDTO().toDTO()
    }

    fun updateUser(id: Long, updatedUser: UserEntity): UserDTO {
        val existingUser = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
        val updated = existingUser.copy(
            name = updatedUser.name,
            role = updatedUser.role,
            registerDate = updatedUser.registerDate
        )
        return userRepository.save(updated).toDTO()
    }

    fun deleteUser(id: Long) = userRepository.deleteById(id)

    fun authenticateUser(email: String, password: String): String? {
        val user = userRepository.findByEmail(email)
        return if (user != null && user.password == password) {
            user.role
        } else {
            null
        }
    }

    private fun UserEntity.toDTO() = UserDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        role = this.role,
        password = this.password,
        registerDate = this.registerDate
    )

    private fun UserDTO.toEntityDTO() = UserEntity(
        id = getAllUsers().size.toLong()+1,
        name = this.name,
        role = this.role,
        email = this.email,
        password = this.password,
        registerDate = LocalDate.now()
    )
}

data class UserDTO(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val registerDate: LocalDate?
)
