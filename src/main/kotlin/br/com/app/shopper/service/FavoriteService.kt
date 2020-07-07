package br.com.app.shopper.service

import br.com.app.shopper.dto.FavoriteDTO
import br.com.app.shopper.model.Favorite
import br.com.app.shopper.repository.FavoriteRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Stream

@Service
class FavoriteService constructor(private val favoriteRepository: FavoriteRepository) {

    fun postFavorite(favorite: Favorite): Favorite {
        return favoriteRepository.save(favorite)
    }

    fun updateFavorite(favorite: Favorite, id: Long): Favorite? {
        val exist = favoriteRepository.existsById(id)
        return if (exist) {
            favoriteRepository.save(favorite)
        } else null
    }

    fun deleteFavorite(id: Long) {
        favoriteRepository.deleteById(id)
    }

    fun getFavoritesByUserId(userId: Long): List<FavoriteDTO>? {
        val existsFavorites = favoriteRepository.findByUser_Id(userId)
        return if (existsFavorites.isPresent()) {
            existsFavorites.get().map { it.toFavoriteDTO() }
        } else null
    }

    fun favoriteExist(id: Long): Boolean {
        return favoriteRepository.existsById(id)
    }
}