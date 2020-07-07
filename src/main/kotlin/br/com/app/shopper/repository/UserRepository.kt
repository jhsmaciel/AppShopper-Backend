package br.com.app.shopper.repository

import br.com.app.shopper.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<User, Long>{
    fun findByEmail(email: String): Optional<User>
}