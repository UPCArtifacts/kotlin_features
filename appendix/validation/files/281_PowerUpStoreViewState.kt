package io.ipoli.android.store.powerup

import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.datetime.daysUntil

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.player.data.Membership
import io.ipoli.android.player.data.Player
import io.ipoli.android.store.powerup.sideeffect.BuyPowerUpCompletedAction
import io.ipoli.android.store.powerup.usecase.BuyPowerUpUseCase
import org.threeten.bp.LocalDate

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 03/15/2018.
 */

sealed class PowerUpStoreAction : Action {
    object Load : PowerUpStoreAction()
    data class Enable(val type: PowerUp.Type) : PowerUpStoreAction() {
        override fun toMap() = mapOf("type" to type.name)
    }

    data class SyncCalendarsSelected(val calendars: Set<Player.Preferences.SyncCalendar>) :
        PowerUpStoreAction() {
        override fun toMap() = mapOf("calendars" to calendars)
    }
}

object PowerUpStoreReducer : BaseViewStateReducer<PowerUpStoreViewState>() {

    override val stateKey = key<PowerUpStoreViewState>()

    override fun reduce(
        state: AppState,
        subState: PowerUpStoreViewState,
        action: Action
    ) =
        when (action) {

            PowerUpStoreAction.Load ->
                state.dataState.player?.let {
                    subState.copy(
                        type = PowerUpStoreViewState.StateType.DATA_CHANGED,
                        powerUps = createPowerUps(it)
                    )
                } ?: subState.copy(type = PowerUpStoreViewState.StateType.LOADING)

            is DataLoadedAction.PlayerChanged ->
                subState.copy(
                    type = PowerUpStoreViewState.StateType.DATA_CHANGED,
                    powerUps = createPowerUps(action.player)
                )

            is PowerUpStoreAction.Enable ->
                subState.copy(type = PowerUpStoreViewState.StateType.LOADING)

            is BuyPowerUpCompletedAction ->
                when (action.result) {
                    is BuyPowerUpUseCase.Result.Bought ->
                        subState.copy(
                            type = PowerUpStoreViewState.StateType.POWER_UP_BOUGHT,
                            powerUp = action.result.powerUp
                        )
                    is BuyPowerUpUseCase.Result.TooExpensive ->
                        subState.copy(
                            type = PowerUpStoreViewState.StateType.POWER_UP_TOO_EXPENSIVE
                        )
                }

            else -> subState
        }

    private fun createPowerUps(player: Player): List<PowerUpItem> {
        val inventory = player.inventory
        return PowerUp.Type.values()
            .filter {
                it == PowerUp.Type.GROWTH
                    || it == PowerUp.Type.TAGS
                    || it == PowerUp.Type.CUSTOM_DURATION
                    || it == PowerUp.Type.TRACK_CHALLENGE_VALUES
                    || it == PowerUp.Type.HABIT_WIDGET
                    || it == PowerUp.Type.CALENDAR_SYNC
                    || it == PowerUp.Type.HABITS
            }
            .map {
                when {
                    inventory.isPowerUpEnabled(it) -> {
                        val p = inventory.getPowerUp(it)!!
                        PowerUpItem.Enabled(
                            type = p.type,
                            daysUntilExpiration = LocalDate.now().daysUntil(p.expirationDate).toInt(),
                            expirationDate = p.expirationDate,
                            showExpirationDate = player.membership == Membership.NONE
                        )
                    }
                    else -> PowerUpItem.Disabled(
                        type = it,
                        coinPrice = it.coinPrice
                    )
                }
            }
    }

    override fun defaultState() = PowerUpStoreViewState(
        type = PowerUpStoreViewState.StateType.LOADING,
        powerUp = PowerUp.Type.GROWTH,
        powerUps = emptyList()
    )
}

sealed class PowerUpItem {
    data class Enabled(
        val type: PowerUp.Type,
        val daysUntilExpiration: Int,
        val expirationDate: LocalDate,
        val showExpirationDate: Boolean
    ) : PowerUpItem()

    data class Disabled(val type: PowerUp.Type, val coinPrice: Int) : PowerUpItem()
}

data class PowerUpStoreViewState(
    val type: StateType,
    val powerUp: PowerUp.Type,
    val powerUps: List<PowerUpItem>
) : BaseViewState() {

    enum class StateType {
        LOADING, POWER_UP_BOUGHT, POWER_UP_TOO_EXPENSIVE, DATA_CHANGED
    }
}