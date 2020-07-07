package br.com.app.shopper.repository

import br.com.app.shopper.model.Favorite
import org.springframework.data.repository.CrudRepository
import java.util.*

interface FavoriteRepository: CrudRepository<Favorite, Long> {
    fun findByUser_Id(userId: Long): Optional<Collection<Favorite>>
}