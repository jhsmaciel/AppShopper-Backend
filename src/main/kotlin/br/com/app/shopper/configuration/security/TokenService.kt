package br.com.app.shopper.configuration.security

import br.com.app.shopper.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*


@Service
class TokenService {
    @Value("\${appshop.jwt.expiration}")
    private val expiration: String? = null
    @Value("\${appshop.jwt.secret}")
    private val secret: String? = null

    fun tokenGenerate(authentication: Authentication): String {
        val logged = authentication.principal as User
        val today = Date()
        val dataExp = Date(today.time + expiration!!.toLong())
        return Jwts.builder()
                .setIssuer("FONOPLAY API")
                .setSubject(logged.id.toString())
                .setIssuedAt(today)
                .setExpiration(dataExp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
    }

    fun isValidToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getIdUser(token: String?): Long {
        val claims: Claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
        return claims.getSubject().toLong()
    }
}