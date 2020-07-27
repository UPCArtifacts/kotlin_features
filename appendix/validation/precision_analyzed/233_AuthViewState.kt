package io.ipoli.android.player.auth

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import io.ipoli.android.Constants
import io.ipoli.android.R
import io.ipoli.android.common.AppState
import io.ipoli.android.common.BaseViewStateReducer
import io.ipoli.android.common.redux.Action
import io.ipoli.android.common.redux.BaseViewState
import io.ipoli.android.habit.predefined.PredefinedHabit
import io.ipoli.android.onboarding.OnboardData
import io.ipoli.android.pet.PetAvatar
import io.ipoli.android.player.auth.AuthViewState.StateType.*
import io.ipoli.android.player.auth.UsernameValidator.ValidationError
import io.ipoli.android.player.auth.UsernameValidator.ValidationError.*
import io.ipoli.android.player.data.Avatar
import io.ipoli.android.quest.RepeatingQuest

/**
 * Created by Polina Zhelyazkova <polina@mypoli.fun>
 * on 2/5/18.
 */
sealed class AuthAction : Action {
    data class Load(val onboardData: OnboardData?) : AuthAction() {
        override fun toMap() = mapOf(
            "repeatingQuests" to onboardData?.repeatingQuests
        )
    }

    data class Loaded(
        val hasPlayer: Boolean,
        val isGuest: Boolean,
        val hasUsername: Boolean,
        val onboardData: OnboardData?
    ) : AuthAction() {
        override fun toMap() = mapOf(
            "hasPlayer" to hasPlayer,
            "isGuest" to isGuest,
            "hasUsername" to hasUsername,
            "repeatingQuests" to onboardData?.repeatingQuests
        )
    }

    data class UserAuthenticated(val user: FirebaseUser, val isNew: Boolean) : AuthAction() {
        override fun toMap() = mapOf("user" to user, "isNew" to isNew)
    }

    data class UsernameValidationFailed(val error: ValidationError) : AuthAction() {
        override fun toMap() = mapOf("error" to error.name)
    }

    data class CompleteSetup(
        val username: String,
        val avatar: Avatar
    ) : AuthAction() {
        override fun toMap() = mapOf("username" to username, "avatar" to avatar.name)
    }

    data class ValidateUsername(val username: String) : AuthAction() {
        override fun toMap() = mapOf("username" to username)
    }

    data class ChangeAvatar(val avatar: Avatar) : AuthAction() {
        override fun toMap() = mapOf("avatar" to avatar.name)
    }

    object NewPlayerLoggedInFromGuest : AuthAction()
    object GuestCreated : AuthAction()
    object PlayerSetupCompleted : AuthAction()
    object ExistingPlayerLoggedIn : AuthAction()
    object ExistingPlayerLoggedInFromGuest : AuthAction()

    object ShowSetUp : AuthAction()
    object UsernameValid : AuthAction()
    object ContinueAsGuest : AuthAction()
    object ShowImportDataError : AuthAction()
}

object AuthReducer : BaseViewStateReducer<AuthViewState>() {

    override val stateKey = key<AuthViewState>()

    override fun reduce(state: AppState, subState: AuthViewState, action: Action) =
        when (action) {
            is AuthAction.Loaded -> {
                val hasPlayer = action.hasPlayer
                val isGuest = action.isGuest
                val hasUsername = action.hasUsername
                val onboardData = action.onboardData
                subState.copy(
                    type = if (!hasPlayer || isGuest) {
                        SHOW_LOGIN
                    } else if (!hasUsername) {
                        SHOW_SETUP
                    } else {
                        throw  IllegalStateException("Player is already authenticated and has username")
                    },
                    isGuest = action.isGuest,
                    repeatingQuests = onboardData?.repeatingQuests ?: subState.repeatingQuests,
                    habits = onboardData?.habits ?: subState.habits
                )
            }

            is AuthAction.UsernameValidationFailed -> {
                subState.copy(
                    type = USERNAME_VALIDATION_ERROR,
                    usernameValidationError = action.error
                )
            }

            AuthAction.UsernameValid -> {
                subState.copy(
                    type = USERNAME_VALID
                )
            }

            AuthAction.GuestCreated -> {
                subState.copy(
                    type = GUEST_CREATED
                )
            }

            AuthAction.PlayerSetupCompleted -> {
                subState.copy(
                    type = PLAYER_SETUP_COMPLETED
                )
            }

            is AuthAction.ExistingPlayerLoggedIn -> {
                subState.copy(
                    type = EXISTING_PLAYER_LOGGED_IN
                )
            }

            is AuthAction.ExistingPlayerLoggedInFromGuest ->
                subState.copy(
                    type = EXISTING_PLAYER_LOGGED_IN_FROM_GUEST
                )

            AuthAction.ShowSetUp ->
                subState.copy(
                    type = SWITCH_TO_SETUP
                )

            AuthAction.NewPlayerLoggedInFromGuest ->
                subState.copy(
                    type = SWITCH_TO_SETUP
                )

            is AuthAction.ChangeAvatar ->
                subState.copy(
                    type = AVATAR_CHANGED,
                    playerAvatar = action.avatar
                )

            is AuthAction.ShowImportDataError ->
                subState.copy(
                    type = SHOW_IMPORT_ERROR
                )

            else -> subState

        }

    override fun defaultState() =
        AuthViewState(
            type = LOADING,
            usernameValidationError = null,
            isGuest = false,
            username = "",
            playerAvatar = Avatar.AVATAR_03,
            avatars = listOf(
                Avatar.AVATAR_03,
                Avatar.AVATAR_02,
                Avatar.AVATAR_01,
                Avatar.AVATAR_04,
                Avatar.AVATAR_05,
                Avatar.AVATAR_06,
                Avatar.AVATAR_07,
                Avatar.AVATAR_11
            ),
            petName = null,
            petAvatar = null,
            repeatingQuests = emptySet(),
            habits = emptySet()
        )
}

data class AuthViewState(
    val type: AuthViewState.StateType,
    val usernameValidationError: ValidationError?,
    val isGuest: Boolean,
    val username: String,
    val playerAvatar: Avatar,
    val avatars: List<Avatar>,
    val petName: String?,
    val petAvatar: PetAvatar?,
    val repeatingQuests: Set<Pair<RepeatingQuest, OnboardData.Tag?>>,
    val habits: Set<Pair<PredefinedHabit, OnboardData.Tag?>>
) : BaseViewState() {
    enum class StateType {
        IDLE,
        LOADING,
        SHOW_LOGIN,
        SHOW_SETUP,
        SWITCH_TO_SETUP,
        USERNAME_VALIDATION_ERROR,
        USERNAME_VALID,
        GUEST_CREATED,
        PLAYER_SETUP_COMPLETED,
        EXISTING_PLAYER_LOGGED_IN,
        EXISTING_PLAYER_LOGGED_IN_FROM_GUEST,
        AVATAR_CHANGED,
        SHOW_IMPORT_ERROR
    }
}

fun AuthViewState.usernameErrorMessage(context: Context) =
    usernameValidationError?.let {
        when (it) {
            EMPTY_USERNAME -> context.getString(R.string.username_is_empty)
            EXISTING_USERNAME -> context.getString(R.string.username_is_taken)
            INVALID_FORMAT -> context.getString(R.string.username_wrong_format)
            INVALID_LENGTH -> context.getString(
                R.string.username_wrong_length,
                Constants.USERNAME_MIN_LENGTH,
                Constants.USERNAME_MAX_LENGTH
            )
        }
    }