package com.example.demo.infrastructure

import com.example.demo.domain.DocumentDTO
import com.example.demo.domain.DocumentService
import com.example.demo.domain.InventoryService
import com.example.demo.domain.MechanicServiceDTO
import com.example.demo.domain.ServiceService
import com.example.demo.domain.StorageDTO
import com.example.demo.domain.UserDTO
import com.example.demo.domain.UserService
import com.example.demo.infrastructure.service.MechanicServiceEntity
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
    fun getAllServices(): List<MechanicServiceEntity> = serviceService.getAllMechanicServices()

    @GetMapping("/services/client/{clientName}")
    fun getServicesByClientName(@PathVariable clientName: String): List<MechanicServiceEntity> {
        return serviceService.getServicesByClientName(clientName)
    }

    @GetMapping("/services/partner/{partnerName}")
    fun getServicesByPartnerName(@PathVariable partnerName: String): List<MechanicServiceEntity> {
        return serviceService.getServicesByPartnerName(partnerName)
    }

    @PostMapping("/services/partner/change-status/{id}")
    fun changeStatus(@PathVariable id: Long): ResponseEntity<MechanicServiceEntity> {
        val updatedService = serviceService.changeStatus(id)
        return ResponseEntity.ok(updatedService)
    }

    @GetMapping("/services/mechanic")
    fun getAllMechanicServices(): List<MechanicServiceDTO> = serviceService.getAllMechanicServicesList()

    @PostMapping("/services/mechanic/add")
    fun createMechanicService(@RequestBody createRequest: MechanicServiceUpdateRequest) {
        return serviceService.createMechanicService(createRequest)
    }

    @GetMapping("/documents")
    fun getAllDocuments(): List<DocumentDTO> = documentService.getAllDocuments()

    @GetMapping("/documents/client/{name}")
    fun getDocumentsByName(@PathVariable name: String): List<DocumentDTO> {
        return documentService.getDocumentsByName(name)
    }

    @GetMapping("/documents/partner/{partnerName}")
    fun getDocumentsByPartnerName(@PathVariable partnerName: String): List<DocumentDTO> {
        return documentService.getDocumentsByPartnerName(partnerName)
    }

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
            ResponseEntity.ok(role)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @DeleteMapping("/users/delete/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    @PostMapping("/users/add")
    fun addUser(@RequestBody userDTO: UserDTO): UserDTO {
        return userService.createUser(userDTO)
    }
}

data class AuthRequestDto(val email: String, val password: String)

data class AuthResponseDto(val role: String, val name: String)
data class MechanicServiceUpdateRequest(
    val orderId: Int,
    val appointmentDate: String,
    val hour: String,
    val partner: String,
    val fullName: String
)