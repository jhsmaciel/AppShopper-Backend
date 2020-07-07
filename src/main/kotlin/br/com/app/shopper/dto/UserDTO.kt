package br.com.app.shopper.dto

import io.swagger.annotations.ApiModel
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@ApiModel
data class UserDTO (
        val id: Long?,
        val name: String,
        val email: String,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        val nascimento: LocalDate?
)