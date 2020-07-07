package br.com.app.shopper.dto

import br.com.app.shopper.model.User
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class FavoriteDTO(
        val id: Long?,
        val full_name: String,
        val description: String?,
        val name: String,
        val login: String,
        val avatar_url: String,
        val user: UserDTO
)