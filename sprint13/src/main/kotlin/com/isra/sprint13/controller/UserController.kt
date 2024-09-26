package com.isra.sprint13.controller

import com.isra.sprint13.model.User
import com.isra.sprint13.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class UserController(private val userService: UserService) {

    @GetMapping("/users/mine")
    fun getUser() : ResponseEntity<*> {
        return ResponseEntity(userService.findCurrent(), HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/users")
    fun addUser(@RequestBody user: User): ResponseEntity<*> {

        return ResponseEntity(userService.save(user), HttpStatus.CREATED)
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/userid/{userid}")
    fun deleteUser(@PathVariable("userid") id: Long): ResponseEntity<*> {

        userService.delete(id)

        return ResponseEntity("""{"message":"user deleted"}""", HttpStatus.OK)
    }

}