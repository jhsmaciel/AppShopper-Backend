package br.com.app.shopper.service

import br.com.app.shopper.form.UserForm
import br.com.app.shopper.model.Favorite
import br.com.app.shopper.model.User
import br.com.app.shopper.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun postUser(userForm: UserForm): User? {
        val userFinded = userRepository.findByEmail(userForm.email)
        userForm.senha = passwordEncoder.encode(userForm.senha)
        return if (userFinded.isPresent()) {
            null
        } else userRepository.save(userForm.toUser())
    }

    fun updateUser(userForm: UserForm, id: Long): User? {
        val exist = userRepository.existsById(id)
        userForm.senha = passwordEncoder.encode(userForm.senha)
        return if (exist) {
            userRepository.save(userForm.toUser(id))
        } else null
    }
}