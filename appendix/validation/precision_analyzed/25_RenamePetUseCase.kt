package io.ipoli.android.pet.usecase

import io.ipoli.android.common.UseCase
import io.ipoli.android.player.data.Player
import io.ipoli.android.player.persistence.PlayerRepository

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 12/13/17.
 */
class RenamePetUseCase(private val playerRepository: PlayerRepository) :
    UseCase<RenamePetUseCase.Params, RenamePetUseCase.Result> {

    data class Params(val name: String)

    sealed class Result {

        data class Renamed(val player: Player) : Result()
        object EmptyName : Result()
    }

    override fun execute(parameters: Params): Result {
        if (parameters.name.isEmpty()) {
            return Result.EmptyName
        }
        val player = playerRepository.find()
        requireNotNull(player)
        val pet = player!!.pet
        require(player.inventory.hasPet(pet.avatar))

        val newPet = pet.copy(
            name = parameters.name
        )

        val newPlayer = player.copy(
            pet = newPet,
            inventory = player.inventory.changePetName(parameters.name, pet.avatar)
        )

        return Result.Renamed(playerRepository.save(newPlayer))
    }

}