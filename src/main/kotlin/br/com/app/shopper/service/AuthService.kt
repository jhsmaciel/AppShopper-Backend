package br.com.app.shopper.service

import br.com.app.shopper.configuration.security.TokenService
import br.com.app.shopper.dto.AuthenticationDTO
import br.com.app.shopper.dto.TokenDTO
import br.com.app.shopper.form.AuthenticationForm
import br.com.app.shopper.model.User
import br.com.app.shopper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class AuthService @Autowired constructor(
        private val tokenService: TokenService,
        private val authenticationManager: AuthenticationManager,
        private val userRepository: UserRepository
) {

    fun authentication(authenticationForm: AuthenticationForm): AuthenticationDTO? {
        try {
            val loginData = UsernamePasswordAuthenticationToken(authenticationForm.username, authenticationForm.password)
            val authenticaton = authenticationManager.authenticate(loginData)
            val token: String = tokenService.tokenGenerate(authenticaton)
            val userOptional: Optional<User> = userRepository.findByEmail(authenticationForm.username)
            val user = userOptional.get()
            return AuthenticationDTO(
                    TokenDTO(token, "Bearer"),
                    user.id!!,
                    user.name,
                    user.email,
                    user.nascimento
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw UsernameNotFoundException("sdas")
        }
    }
}