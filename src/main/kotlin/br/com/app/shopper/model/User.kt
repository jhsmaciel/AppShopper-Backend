package br.com.app.shopper.model

import br.com.app.shopper.dto.UserDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import javax.persistence.*

@Entity
@ApiModel
@Table
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val name: String,
        val email: String,
        /** Data de agendamento do pagamento **/
        /**
         * The most common ISO Date Format {@code yyyy-MM-dd},
         * e.g. "2000-10-31".
         */
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @ApiModelProperty(example = "yyyy-MM-dd", dataType = "date")
        val nascimento: LocalDate?,
        val senha: String,
        @JsonIgnore
        @OneToMany
        val favorites: List<Favorite> = arrayListOf()
) : UserDetails {

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as User
        if (id == null) {
            if (other.id != null) return false
        } else if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (id == null) 0 else id.hashCode()
        return result
    }

    override fun getPassword(): String {
        return this.senha!!
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getUsername(): String {
        return email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun toUserDTO(): UserDTO {
        return UserDTO(this.id, this.name, this.email, this.nascimento)
    }
}