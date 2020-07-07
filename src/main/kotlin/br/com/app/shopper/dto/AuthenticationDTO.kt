package br.com.app.shopper.dto

import java.time.LocalDate

data class AuthenticationDTO(
        val token: TokenDTO,
        val id: Long,
        val name: String,
        val email: String,
        val nascimento: LocalDate?
)