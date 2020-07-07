package br.com.app.shopper.form

import br.com.app.shopper.model.User
import java.time.LocalDate

data class UserForm (
        val name: String,
        val email: String,
        val nascimento: LocalDate,
        var senha: String
) {

    fun toUser(): User {
        return User(null, this.name, this.email, this.nascimento, this.senha)
    }

    fun toUser(id: Long): User {
        return User(id, this.name, this.email, this.nascimento, this.senha)
    }
}