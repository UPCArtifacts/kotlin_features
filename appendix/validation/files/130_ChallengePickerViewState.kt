package io.ipoli.android.challenge.picker

import io.ipoli.android.challenge.entity.Challenge
import io.ipoli.android.challenge.picker.ChallengePickerViewState.StateType.*
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.DataLoadedAction
import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.pet.PetAvatar

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 4/11/18.
 */
sealed class ChallengePickerAction : Action {
    object Select : ChallengePickerAction()
    data class Load(val challenge: Challenge?) : ChallengePickerAction() {
        override fun toMap() = mapOf("challenge" to challenge)
    }

    data class ChangeSelected(val challenge: Challenge) : ChallengePickerAction() {
        override fun toMap() = mapOf("challenge" to challenge)
    }
}

object ChallengePickerReducer : BaseViewStateReducer<ChallengePickerViewState>() {
    override val stateKey = key<ChallengePickerViewState>()

    override fun reduce(
        state: AppState,
        subState: ChallengePickerViewState,
        action: Action
    ) = when (action) {
        is ChallengePickerAction.Load -> {
            val challenges = state.dataState.challenges
            subState.copy(
                type = if (challenges == null) LOADING else DATA_CHANGED,
                selectedChallenge = action.challenge,
                challenges = challenges,
                petAvatar = state.dataState.player!!.pet.avatar,
                showEmpty = challenges != null && challenges.isEmpty()
            )
        }

        is DataLoadedAction.ChallengesChanged -> {
            subState.copy(
                type = DATA_CHANGED,
                challenges = action.challenges,
                showEmpty = action.challenges.isEmpty()
            )
        }


        is ChallengePickerAction.ChangeSelected -> {
            subState.copy(
                type = CHALLENGE_CHANGED,
                selectedChallenge = action.challenge
            )
        }

        is ChallengePickerAction.Select -> {
            subState.copy(
                type = CHALLENGE_SELECTED
            )
        }

        else -> subState
    }

    override fun defaultState() = ChallengePickerViewState(
        type = LOADING,
        petAvatar = PetAvatar.ELEPHANT,
        challenges = listOf(),
        selectedChallenge = null,
        showEmpty = false
    )

}

data class ChallengePickerViewState(
    val type: StateType,
    val petAvatar: PetAvatar,
    val challenges: List<Challenge>?,
    val selectedChallenge: Challenge?,
    val showEmpty: Boolean
) : BaseViewState() {
    enum class StateType {
        LOADING,
        DATA_CHANGED,
        CHALLENGE_CHANGED,
        CHALLENGE_SELECTED
    }
}
