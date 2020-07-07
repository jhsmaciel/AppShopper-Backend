package br.com.app.shopper.controller

import br.com.app.shopper.dto.UserDTO
import br.com.app.shopper.form.UserForm
import br.com.app.shopper.service.UserService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["users"], produces = ["application/json"], consumes = ["application/json"])
@Api( tags = ["User Controller"])
class UserController @Autowired constructor(private val userService: UserService) {

    @PostMapping
    fun postUser(@RequestBody userForm: UserForm): ResponseEntity<UserDTO> {
        val user = userService.postUser(userForm) ?: return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        return ResponseEntity.status(HttpStatus.CREATED).body(user.toUserDTO());
    }

    @PutMapping(value=["/{userId}"])
    fun putUser(@PathVariable userId: Long, @RequestBody userForm: UserForm): ResponseEntity<UserDTO> {
        val user = userService.updateUser(userForm, userId) ?: return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build()
        return ResponseEntity.status(HttpStatus.OK).body(user.toUserDTO())
    }

}