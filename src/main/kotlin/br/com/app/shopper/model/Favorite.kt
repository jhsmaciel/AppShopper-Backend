package br.com.app.shopper.model

import br.com.app.shopper.dto.FavoriteDTO
import javax.persistence.*

@Entity
data class Favorite (
        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val full_name: String,
    val description: String?,
    val name: String,
    val login: String,
    val avatar_url: String,
    @ManyToOne
    @JoinColumn(name= "user_id")
    val user: User
){
    fun toFavoriteDTO(): FavoriteDTO {
        return FavoriteDTO(this.id, this.full_name, this.description, this.name, this.login, this.avatar_url, this.user.toUserDTO())
    }
}