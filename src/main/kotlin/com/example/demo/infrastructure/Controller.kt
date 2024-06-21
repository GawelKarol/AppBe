package com.example.demo.infrastructure

import com.example.demo.domain.DocumentDTO
import com.example.demo.domain.DocumentService
import com.example.demo.domain.InventoryService
import com.example.demo.domain.ServiceDTO
import com.example.demo.domain.ServiceService
import com.example.demo.domain.StorageDTO
import com.example.demo.domain.UserDTO
import com.example.demo.domain.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = arrayOf("http://localhost:3000"))
class Controller(
    private val serviceService: ServiceService,
    private val documentService: DocumentService,
    private val inventoryService: InventoryService,
    private val userService: UserService
) {

    @GetMapping("/services")
    fun getAllServices(): List<ServiceDTO> = serviceService.getAllServices()

    @GetMapping("/documents")
    fun getAllDocuments(): List<DocumentDTO> = documentService.getAllDocuments()

    @GetMapping("/storage")
    fun getAllStorage(): List<StorageDTO> = inventoryService.getAllStorage()

    @PostMapping("/storage/add")
    fun addStorageItem(@RequestBody item: StorageDTO): StorageDTO {
        return inventoryService.addStorageItem(item)
    }

    @GetMapping("/users")
    fun getAllUsers(): List<UserDTO> = userService.getAllUsers()

    @PostMapping("/users/login")
    fun login(@RequestBody authRequest: AuthRequestDto): ResponseEntity<AuthResponseDto> {
        val role = userService.authenticateUser(authRequest.email, authRequest.password)
        return if (role != null) {
            ResponseEntity.ok(AuthResponseDto(role))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @DeleteMapping("/users/delete/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    @PostMapping("/users/add")
    fun addUser(@RequestBody userDTO: UserDTO) : UserDTO {
       return userService.createUser(userDTO)
    }
}

data class AuthRequestDto(val email: String, val password: String)

data class AuthResponseDto(val role: String)