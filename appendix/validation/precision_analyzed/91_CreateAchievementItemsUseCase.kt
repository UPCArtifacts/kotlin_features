package io.ipoli.android.achievement.usecase

import io.ipoli.android.achievement.Achievement
import io.ipoli.android.common.UseCase
import io.ipoli.android.player.data.Player

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 06/11/2018.
 */
class CreateAchievementItemsUseCase :
    UseCase<CreateAchievementItemsUseCase.Params, List<CreateAchievementItemsUseCase.AchievementListItem>> {

    override fun execute(parameters: Params): List<AchievementListItem> {

        val p = parameters.player

        val stats = p.statistics

        val at = p.achievements.map { it.achievement }.toSet()

        val ais = listOf(
            AchievementItem.QuestCompleted(
                stats.questCompletedCount.toInt(),
                singleLevelFrom(Achievement.FIRST_QUEST_COMPLETED, at)
            ),
            AchievementItem.CompletedQuestsInADay(
                stats.questCompletedCountForToday.toInt(),
                singleLevelFrom(Achievement.COMPLETE_10_QUESTS_IN_A_DAY, at)
            ),
            AchievementItem.KeepPetHappyStreak(
                stats.petHappyStateStreak.toInt(),
                currentLevelFrom(
                    Achievement.KEEP_PET_HAPPY_5_DAY_STREAK,
                    Achievement.KEEP_PET_HAPPY_15_DAY_STREAK,
                    Achievement.KEEP_PET_HAPPY_40_DAY_STREAK,
                    at
                )
            ),
            AchievementItem.AwesomenessScoreStreak(
                stats.awesomenessScoreStreak.toInt(),
                currentLevelFrom(
                    Achievement.AWESOMENESS_SCORE_5_DAY_STREAK,
                    Achievement.AWESOMENESS_SCORE_20_DAY_STREAK,
                    Achievement.AWESOMENESS_SCORE_50_DAY_STREAK,
                    at
                )
            ),
            AchievementItem.FocusHoursStreak(
                stats.focusHoursStreak.toInt(),
                currentLevelFrom(
                    Achievement.FOCUS_HOURS_5_DAY_STREAK,
                    Achievement.FOCUS_HOURS_20_DAY_STREAK,
                    Achievement.FOCUS_HOURS_50_DAY_STREAK,
                    at
                )
            ),
            AchievementItem.PlanDayStreak(
                stats.planDayStreak.count.toInt(),
                currentLevelFrom(
                    Achievement.PLAN_DAY_5_DAY_STREAK,
                    Achievement.PLAN_DAY_20_DAY_STREAK,
                    Achievement.PLAN_DAY_50_DAY_STREAK,
                    at
                )
            ),
            AchievementItem.GemsConverted(
                stats.gemConvertedCount.toInt(),
                currentLevelFrom(
                    Achievement.CONVERT_1_GEM,
                    Achievement.CONVERT_20_GEMS,
                    Achievement.CONVERT_50_GEMS,
                    at
                )
            ),
            AchievementItem.LevelUp(
                p.level,
                currentLevelFrom(
                    Achievement.REACH_LEVEL_10,
                    Achievement.REACH_LEVEL_30,
                    Achievement.REACH_LEVEL_50,
                    at
                )
            ),
            AchievementItem.CoinsInInventory(
                p.coins,
                currentLevelFrom(
                    Achievement.HAVE_1K_LIFE_COINS_IN_INVENTORY,
                    Achievement.HAVE_5K_LIFE_COINS_IN_INVENTORY,
                    Achievement.HAVE_10K_LIFE_COINS_IN_INVENTORY,
                    at
                )
            ),
            AchievementItem.InviteFriend(
                stats.friendInvitedCount.toInt(),
                currentLevelFrom(
                    Achievement.INVITE_1_FRIEND,
                    Achievement.INVITE_5_FRIENDS,
                    Achievement.INVITE_20_FRIENDS,
                    at
                )
            ),
            AchievementItem.Gain999XPInADay(
                stats.experienceForToday.toInt(),
                singleLevelFrom(Achievement.GAIN_999_XP_IN_A_DAY, at)
            ),
            AchievementItem.FirstPetItemEquipped(
                stats.petItemEquippedCount.toInt(),
                singleLevelFrom(Achievement.FIRST_PET_ITEM_EQUIPPED, at)
            ),
            AchievementItem.FirstRepeatingQuestCreated(
                stats.repeatingQuestCreatedCount.toInt(),
                singleLevelFrom(Achievement.FIRST_REPEATING_QUEST_CREATED, at)
            ),
            AchievementItem.FirstChallengeCreated(
                stats.challengeCreatedCount.toInt(),
                singleLevelFrom(Achievement.FIRST_CHALLENGE_CREATED, at)
            ),
            AchievementItem.CompleteDailyChallengeStreak(
                stats.dailyChallengeCompleteStreak.count.toInt(),
                currentLevelFrom(
                    Achievement.COMPLETE_DAILY_CHALLENGE,
                    Achievement.COMPLETE_DAILY_CHALLENGE_10_DAY_STREAK,
                    Achievement.COMPLETE_DAILY_CHALLENGE_30_DAY_STREAK,
                    at
                )
            ),
            AchievementItem.CompleteChallenge(
                stats.challengeCompletedCount.toInt(),
                currentLevelFrom(
                    Achievement.COMPLETE_CHALLENGE,
                    Achievement.COMPLETE_5_CHALLENGES,
                    Achievement.COMPLETE_15_CHALLENGES,
                    at
                )
            ),
            AchievementItem.FeedPetWithPoop(
                stats.petFedWithPoopCount.toInt(),
                singleLevelFrom(Achievement.PET_FED_WITH_POOP, at)
            ),
            AchievementItem.FeedPet(
                stats.petFedCount.toInt(),
                singleLevelFrom(Achievement.PET_FED, at)

            ),
            AchievementItem.BecomePro(
                stats.joinMembershipCount.toInt(),
                singleLevelFrom(Achievement.BECAME_PRO, at)
            ),
            AchievementItem.RevivePet(
                stats.petRevivedCount.toInt(),
                singleLevelFrom(Achievement.PET_REVIVED, at)
            ),
            AchievementItem.FirstAvatarChanged(
                stats.avatarChangeCount.toInt(),
                singleLevelFrom(Achievement.FIRST_AVATAR_CHANGED, at)
            ),
            AchievementItem.FirstPowerUpActivated(
                stats.powerUpActivatedCount.toInt(),
                singleLevelFrom(Achievement.FIRST_POWER_UP_ACTIVATED, at)
            ),
            AchievementItem.FirstPetChanged(
                stats.petChangeCount.toInt(),
                singleLevelFrom(Achievement.FIRST_PET_CHANGED, at)
            ),
            AchievementItem.PetDied(
                stats.petDiedCount.toInt(),
                singleLevelFrom(Achievement.PET_DIED, at)
            ),
            AchievementItem.CompleteQuestFor100DaysInARow(
                stats.questCompletedStreak.count.toInt(),
                singleLevelFrom(Achievement.COMPLETE_QUEST_FOR_100_DAYS_IN_A_ROW, at)
            ),
            AchievementItem.FeedbackSent(
                stats.feedbackSentCount.toInt(),
                singleLevelFrom(Achievement.FEEDBACK_SENT, at)
            )
        )

        val (unlocked, locked) = ais.partition { it.isUnlocked }

        val res = mutableListOf<AchievementListItem>()

        if (locked.isNotEmpty()) {
            res.add(AchievementListItem.LockedSection)
            res.addAll(locked.map { AchievementListItem.LockedItem(it) })
        }

        if (unlocked.isNotEmpty()) {
            res.add(AchievementListItem.UnlockedSection)
            res.addAll(unlocked.map { AchievementListItem.UnlockedItem(it) })
        }

        return res
    }

    private fun singleLevelFrom(achievement: Achievement, at: Set<Achievement>) =
        if (at.contains(achievement)) 1 else 0

    private fun currentLevelFrom(
        level1Achievement: Achievement,
        level2Achievement: Achievement,
        level3Achievement: Achievement,
        unlockedAchievements: Set<Achievement>
    ) =
        when {
            unlockedAchievements.contains(level3Achievement) -> 3
            unlockedAchievements.contains(level2Achievement) -> 2
            unlockedAchievements.contains(level1Achievement) -> 1
            else -> 0
        }

    data class Params(val player: Player)

    sealed class AchievementListItem {
        object LockedSection : AchievementListItem()
        data class LockedItem(val achievementItem: AchievementItem) : AchievementListItem()
        object UnlockedSection : AchievementListItem()
        data class UnlockedItem(val achievementItem: AchievementItem) : AchievementListItem()
    }

    sealed class AchievementItem(
        open val progress: Int,
        open val currentLevel: Int = 0,
        val levelProgressRequirements: Map<Int, Int> = mapOf(1 to 1)
    ) {

        val isUnlocked
            get() = currentLevel == levelProgressRequirements.size

        val hasProgress
l            get() = isMultiLevel || levelProgressRequirements[1]!! > 1

        val isMultiLevel
            get() = levelProgressRequirements.size > 1

        val progressForNextLevel
            get() = if (!hasProgress || isUnlocked) -1 else levelProgressRequirements[currentLevel + 1]!!

        data class QuestCompleted(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) :
            AchievementItem(
                progress = progress,
                currentLevel = currentLevel
            )

        data class CompletedQuestsInADay(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) :
            AchievementItem(
                progress = progress,
                levelProgressRequirements = mapOf(1 to 10),
                currentLevel = currentLevel
            )

        data class KeepPetHappyStreak(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress,
            levelProgressRequirements = mapOf(
                1 to 5,
                2 to 15,
                3 to 40
            ),
            currentLevel = currentLevel
        )

        data class AwesomenessScoreStreak(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 5,
                2 to 20,
                3 to 50
            ),
            currentLevel = currentLevel
        )

        data class FocusHoursStreak(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 5,
                2 to 20,
                3 to 50
            ),
            currentLevel = currentLevel
        )

        data class PlanDayStreak(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 5,
                2 to 20,
                3 to 50
            ),
            currentLevel = currentLevel
        )

        data class GemsConverted(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1,
                2 to 20,
                3 to 50
            ),
            currentLevel = currentLevel
        )

        data class LevelUp(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 10,
                2 to 30,
                3 to 50
            ),
            currentLevel = currentLevel
        )

        data class CoinsInInventory(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1000,
                2 to 5000,
                3 to 10000
            ),
            currentLevel = currentLevel
        )

        data class InviteFriend(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1,
                2 to 5,
                3 to 20
            ),
            currentLevel = currentLevel
        )

        data class Gain999XPInADay(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 999
            ),
            currentLevel = currentLevel
        )

        data class FirstPetItemEquipped(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FirstRepeatingQuestCreated(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FirstChallengeCreated(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class CompleteDailyChallengeStreak(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1,
                2 to 10,
                3 to 30
            ),
            currentLevel = currentLevel
        )

        data class CompleteChallenge(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1,
                2 to 5,
                3 to 15
            ),
            currentLevel = currentLevel
        )

        data class FeedPetWithPoop(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FeedPet(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class BecomePro(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class RevivePet(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FirstAvatarChanged(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FirstPowerUpActivated(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class FirstPetChanged(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class PetDied(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

        data class CompleteQuestFor100DaysInARow(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 100
            ),
            currentLevel = currentLevel
        )

        data class FeedbackSent(
            override val progress: Int,
            override val currentLevel: Int = 0
        ) : AchievementItem(
            progress = progress,
            levelProgressRequirements = mapOf(
                1 to 1
            ),
            currentLevel = currentLevel
        )

    }
}