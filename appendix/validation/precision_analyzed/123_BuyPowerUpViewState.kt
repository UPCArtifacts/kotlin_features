package io.ipoli.android.store.powerup.buy

import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction

import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.store.powerup.PowerUp
import io.ipoli.android.store.powerup.sideeffect.BuyPowerUpCompletedAction
import io.ipoli.android.store.powerup.usecase.BuyPowerUpUseCase

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 03/21/2018.
 */

sealed class BuyPowerUpAction : Action {
    object Load : BuyPowerUpAction()
    data class Buy(val powerUp: PowerUp.Type) : BuyPowerUpAction() {
        override fun toMap() = mapOf("powerUp" to powerUp.name)
    }
}

object BuyPowerUpReducer : BaseViewStateReducer<BuyPowerUpViewState>() {
    override fun reduce(
        state: AppState,
        subState: BuyPowerUpViewState,
        action: Action
    ) =
        when (action) {
            is BuyPowerUpAction.Load ->
                state.dataState.player?.let {
                    subState.copy(
                        type = BuyPowerUpViewState.StateType.COINS_CHANGED,
                        coins = it.coins
                    )
                } ?: subState.copy(type = BuyPowerUpViewState.StateType.LOADING)

            is DataLoadedAction.PlayerChanged ->
                subState.copy(
                    type = BuyPowerUpViewState.StateType.COINS_CHANGED,
                    coins = action.player.coins
                )

            is BuyPowerUpCompletedAction ->
                when (action.result) {
                    is BuyPowerUpUseCase.Result.Bought ->
                        subState.copy(
                            type = BuyPowerUpViewState.StateType.POWER_UP_BOUGHT,
                            powerUp = action.result.powerUp
                        )

                    is BuyPowerUpUseCase.Result.TooExpensive ->
                        subState.copy(
                            type = BuyPowerUpViewState.StateType.POWER_UP_TOO_EXPENSIVE
                        )
                }

            else -> subState
        }

    override fun defaultState() = BuyPowerUpViewState(
        type = BuyPowerUpViewState.StateType.LOADING,
        powerUp = PowerUp.Type.GROWTH,
        coins = -1
    )

    override val stateKey get() = key<BuyPowerUpViewState>()
}

data class BuyPowerUpViewState(val type: StateType, val powerUp: PowerUp.Type, val coins: Int) :
    BaseViewState() {

    enum class StateType { LOADING, POWER_UP_BOUGHT, POWER_UP_TOO_EXPENSIVE, COINS_CHANGED }
}