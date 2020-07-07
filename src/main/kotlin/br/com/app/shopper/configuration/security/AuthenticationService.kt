package br.com.app.shopper.configuration.security

import br.com.app.shopper.model.User
import br.com.app.shopper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthenticationService @Autowired constructor(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Optional<User> = userRepository.findByEmail(username)
        if (user.isPresent()) {
            return user.get()
        }
        throw UsernameNotFoundException("Dados inválidos")
    }
}