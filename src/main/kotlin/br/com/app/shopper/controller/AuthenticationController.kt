package br.com.app.shopper.controller

import br.com.app.shopper.dto.AuthenticationDTO
import br.com.app.shopper.form.AuthenticationForm
import br.com.app.shopper.service.AuthService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/login"], produces = ["application/json"], consumes = ["application/json"])
@Api( tags = ["Authentication Controller"])
class AuthenticationController @Autowired constructor(private val authService: AuthService) {

    @PostMapping
    fun authentication(@RequestBody authenticationForm: AuthenticationForm): ResponseEntity<AuthenticationDTO> {
        val authenticationDTO = authService.authentication(authenticationForm) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.OK).body(authenticationDTO)
    }
}