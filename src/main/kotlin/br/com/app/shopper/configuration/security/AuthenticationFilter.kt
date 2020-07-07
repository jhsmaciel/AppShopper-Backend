package br.com.app.shopper.configuration.security

import br.com.app.shopper.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
        private val tokenService: TokenService,
        private val repository: UserRepository
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = getToken(request)
        val valido: Boolean = tokenService.isValidToken(token)
        if (valido) {
            clientAuthentication(token)
        }
        filterChain.doFilter(request, response)
    }

    private fun clientAuthentication(token: String?) {
        val idUsuario = tokenService.getIdUser(token)
        val user = repository.findById(idUsuario).get()
        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        return if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            null
        } else token.substring(7, token.length)
    }

}