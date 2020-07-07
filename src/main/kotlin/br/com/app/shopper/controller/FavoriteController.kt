package br.com.app.shopper.controller

import br.com.app.shopper.dto.FavoriteDTO
import br.com.app.shopper.form.FavoriteForm
import br.com.app.shopper.model.Favorite
import br.com.app.shopper.repository.UserRepository
import br.com.app.shopper.service.FavoriteService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(produces = ["application/json"])
@Api( tags = ["Favorite Controller"])
class FavoriteController @Autowired constructor(private val favoriteService: FavoriteService,
                                                private val userRepository: UserRepository) {

    @GetMapping(value = ["users/{userId}/favorites"])
    fun getFavoritesById(@PathVariable userId: Long): ResponseEntity<List<FavoriteDTO>> {
        val favoritesByUserId = favoriteService.getFavoritesByUserId(userId)
                ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.status(HttpStatus.OK).body(favoritesByUserId);
    }

    @PostMapping(value = ["users/{userId}/favorites"])
    fun postFavorite(@RequestBody favoriteForm: FavoriteForm, @PathVariable userId: Long):
            ResponseEntity<Favorite> {
        val user = userRepository.findById(userId)
        if(!user.isPresent) return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.status(HttpStatus.CREATED).body(favoriteService.postFavorite(favoriteForm.toFavorite(user.get())))
    }

    @DeleteMapping(value = ["users/{userId}/favorites/{favoriteId}"])
    fun deleteFavorite(@PathVariable userId: Long, @PathVariable favoriteId: Long):
            ResponseEntity<Favorite> {
        val user = userRepository.findById(userId)
        if(!user.isPresent || !favoriteService.favoriteExist(favoriteId)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        favoriteService.deleteFavorite(favoriteId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}