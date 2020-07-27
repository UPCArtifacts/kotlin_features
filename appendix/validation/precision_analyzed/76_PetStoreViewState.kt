package io.ipoli.android.pet.store

import io.ipoli.android.R
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.pet.AndroidPetAvatar
import io.ipoli.android.pet.PetAvatar
import io.ipoli.android.pet.PetState
import io.ipoli.android.player.data.Player

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 12/4/17.
 */
sealed class PetStoreAction : Action {
    object Load : PetStoreAction()
    data class BuyPet(val pet: PetAvatar) : PetStoreAction() {
        override fun toMap() = mapOf("pet" to pet)
    }

    data class ChangePet(val pet: PetAvatar) : PetStoreAction() {
        override fun toMap() = mapOf("pet" to pet)
    }
    object PetBought : PetStoreAction()
    object PetTooExpensive : PetStoreAction()
}

object PetStoreReducer : BaseViewStateReducer<PetStoreViewState>() {

    override val stateKey = key<PetStoreViewState>()

    override fun reduce(
        state: AppState,
        subState: PetStoreViewState,
        action: Action
    ): PetStoreViewState {
        val petStoreState = subState.copy(
            playerGems = state.dataState.player?.gems ?: 0
        )
        return when (action) {
            PetStoreAction.Load -> {
                state.dataState.player?.let {
                    createDataChangedState(petStoreState, it)
                } ?: petStoreState
            }

            is DataLoadedAction.PlayerChanged -> {
                createDataChangedState(petStoreState, action.player)
            }

            is PetStoreAction.BuyPet -> {
                petStoreState.copy(
                    type = PetStoreViewState.StateType.LOADING
                )
            }

            PetStoreAction.PetBought -> {
                petStoreState.copy(
                    type = PetStoreViewState.StateType.PET_BOUGHT
                )
            }

            PetStoreAction.PetTooExpensive -> {
                petStoreState.copy(
                    type = PetStoreViewState.StateType.PET_TOO_EXPENSIVE
                )
            }

            is PetStoreAction.ChangePet -> {
                petStoreState.copy(
                    type = PetStoreViewState.StateType.LOADING
                )
            }
            else -> petStoreState
        }
    }

    private fun createDataChangedState(
        petStoreState: PetStoreViewState,
        player: Player
    ): PetStoreViewState {
        return petStoreState.copy(
            type = PetStoreViewState.StateType.DATA_CHANGED,
            pets = createPetModels(player)
        )
    }

    private fun createPetModels(player: Player) =
        PetAvatar.values().map {
            PetStoreViewState.PetModel(
                avatar = it,
                isBought = player.hasPet(it),
                isCurrent = player.pet.avatar == it,
                isLocked = (it == PetAvatar.DOG && !player.hasPet(PetAvatar.DOG))
            )
        }

    override fun defaultState() =
        PetStoreViewState(
            type = PetStoreViewState.StateType.LOADING,
            playerGems = 0,
            pets = listOf()
        )
}

data class PetStoreViewState(
    val type: StateType = StateType.DATA_CHANGED,
    val playerGems: Int = 0,
    val pets: List<PetModel> = listOf()
) : BaseViewState() {
    data class PetModel(
        val avatar: PetAvatar,
        val isBought: Boolean,
        val isCurrent: Boolean,
        val isLocked: Boolean
    )

    enum class StateType {
        LOADING,
        DATA_CHANGED,
        PET_TOO_EXPENSIVE,
        PET_BOUGHT,
        PET_CHANGED
    }
}


fun PetStoreViewState.PetModel.toAndroidPetModel(): PetStoreViewController.PetViewModel {
    val androidAvatar = AndroidPetAvatar.valueOf(avatar.name)

    return when {
        isCurrent -> {
            PetStoreViewController.PetViewModel(
                avatar = avatar,
                name = androidAvatar.petName,
                image = androidAvatar.image,
                price = avatar.gemPrice.toString(),
                description = androidAvatar.description,
                actionText = null,
                moodImage = androidAvatar.stateImage[PetState.HAPPY]!!,
                showAction = false,
                showIsCurrent = true,
                action = null
            )
        }

        isBought -> {
            PetStoreViewController.PetViewModel(
                avatar = avatar,
                name = androidAvatar.petName,
                image = androidAvatar.image,
                price = avatar.gemPrice.toString(),
                description = androidAvatar.description,
                actionText = R.string.store_pet_in_inventory,
                moodImage = androidAvatar.stateImage[PetState.GOOD]!!,
                showAction = true,
                showIsCurrent = false,
                action = PetStoreViewController.PetViewModel.Action.CHANGE
            )
        }

        isLocked -> {
            PetStoreViewController.PetViewModel(
                avatar = avatar,
                name = androidAvatar.petName,
                image = androidAvatar.image,
                price = avatar.gemPrice.toString(),
                description = androidAvatar.description,
                actionText = R.string.unlock,
                moodImage = androidAvatar.stateImage[PetState.GOOD]!!,
                showAction = true,
                showIsCurrent = false,
                action = PetStoreViewController.PetViewModel.Action.UNLOCK
            )
        }

        else -> {
            PetStoreViewController.PetViewModel(
                avatar = avatar,
                name = androidAvatar.petName,
                image = androidAvatar.image,
                price = avatar.gemPrice.toString(),
                description = androidAvatar.description,
                actionText = R.string.store_buy_pet,
                moodImage = androidAvatar.stateImage[PetState.GOOD]!!,
                showAction = true,
                showIsCurrent = false,
                action = PetStoreViewController.PetViewModel.Action.BUY
            )
        }
    }
}