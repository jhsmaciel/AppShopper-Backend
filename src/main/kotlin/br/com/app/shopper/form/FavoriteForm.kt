package br.com.app.shopper.form

import br.com.app.shopper.model.Favorite
import br.com.app.shopper.model.User

data class FavoriteForm(
        val full_name: String,
        val description: String?,
        val name: String,
        val login: String,
        val avatar_url: String
){
    fun toFavorite(user: User): Favorite {
        return Favorite(null, this.full_name, this.description, this.name, this.login, this.avatar_url, user)
    }
}