package io.ipoli.android.common.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import com.amplitude.api.Amplitude
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.ipoli.android.achievement.job.AndroidShowUnlockedAchievementsScheduler
import io.ipoli.android.achievement.job.AndroidUpdateAchievementProgressScheduler
import io.ipoli.android.achievement.job.ShowUnlockedAchievementsScheduler
import io.ipoli.android.achievement.job.UpdateAchievementProgressScheduler
import io.ipoli.android.achievement.middleware.AchievementProgressMiddleWare
import io.ipoli.android.achievement.sideeffect.AchievementListSideEffectHandler
import io.ipoli.android.achievement.usecase.CreateAchievementItemsUseCase
import io.ipoli.android.achievement.usecase.UnlockAchievementsUseCase
import io.ipoli.android.achievement.usecase.UpdateAchievementProgressUseCase
import io.ipoli.android.achievement.usecase.UpdatePlayerStatsUseCase
import io.ipoli.android.challenge.persistence.ChallengeRepository
import io.ipoli.android.challenge.persistence.RoomChallengeRepository
import io.ipoli.android.challenge.preset.persistence.FirestorePresetChallengeRepository
import io.ipoli.android.challenge.preset.persistence.PresetChallengeRepository
import io.ipoli.android.challenge.preset.sideeffect.PresetChallengeSideEffectHandler
import io.ipoli.android.challenge.preset.usecase.UnlockPresetChallengeUseCase
import io.ipoli.android.challenge.sideeffect.ChallengeSideEffectHandler
import io.ipoli.android.challenge.usecase.*
import io.ipoli.android.common.*
import io.ipoli.android.common.analytics.EventLogger
import io.ipoli.android.common.analytics.SimpleEventLogger
import io.ipoli.android.common.billing.BillingRequestExecutor
import io.ipoli.android.common.billing.BillingResponseHandler
import io.ipoli.android.common.image.AndroidImageLoader
import io.ipoli.android.common.image.ImageLoader
import io.ipoli.android.common.job.AndroidResetDayScheduler
import io.ipoli.android.common.job.ResetDayScheduler
import io.ipoli.android.common.middleware.LogEventsMiddleWare
import io.ipoli.android.common.migration.DataExporter
import io.ipoli.android.common.migration.DataImporter
import io.ipoli.android.common.migration.FirestoreToLocalPlayerMigrator
import io.ipoli.android.common.migration.MigrationSideEffectHandler
import io.ipoli.android.common.permission.AndroidPermissionChecker
import io.ipoli.android.common.permission.PermissionChecker
import io.ipoli.android.common.persistence.EntityReminderRepository
import io.ipoli.android.common.persistence.MyPoliRoomDatabase
import io.ipoli.android.common.persistence.RoomEntityReminderRepository
import io.ipoli.android.common.rate.AndroidRatePopupScheduler
import io.ipoli.android.common.rate.RatePopupScheduler
import io.ipoli.android.common.redux.CoroutineSideEffectHandlerExecutor
import io.ipoli.android.common.redux.StateStore
import io.ipoli.android.common.sideeffect.LoadAllDataSideEffectHandler
import io.ipoli.android.common.text.CalendarFormatter
import io.ipoli.android.common.view.PetMessageSideEffectHandler
import io.ipoli.android.dailychallenge.data.persistence.DailyChallengeRepository
import io.ipoli.android.dailychallenge.data.persistence.RoomDailyChallengeRepository
import io.ipoli.android.dailychallenge.job.AndroidDailyChallengeCompleteScheduler
import io.ipoli.android.dailychallenge.job.DailyChallengeCompleteScheduler
import io.ipoli.android.dailychallenge.sideeffect.DailyChallengeSideEffectHandler
import io.ipoli.android.dailychallenge.usecase.CheckDailyChallengeProgressUseCase
import io.ipoli.android.dailychallenge.usecase.CheckForDailyChallengeCompletionUseCase
import io.ipoli.android.dailychallenge.usecase.LoadDailyChallengeUseCase
import io.ipoli.android.dailychallenge.usecase.SaveDailyChallengeQuestIdsUseCase
import io.ipoli.android.event.persistence.AndroidCalendarEventRepository
import io.ipoli.android.event.persistence.AndroidCalendarRepository
import io.ipoli.android.event.persistence.CalendarRepository
import io.ipoli.android.event.persistence.EventRepository
import io.ipoli.android.event.sideeffect.CalendarSideEffectHandler
import io.ipoli.android.event.usecase.FindEventsBetweenDatesUseCase
import io.ipoli.android.event.usecase.SaveSyncCalendarsUseCase
import io.ipoli.android.friends.feed.persistence.AndroidPostRepository
import io.ipoli.android.friends.feed.persistence.FirebaseStorageImageRepository
import io.ipoli.android.friends.feed.persistence.ImageRepository
import io.ipoli.android.friends.feed.persistence.PostRepository
import io.ipoli.android.friends.feed.sideeffect.FeedSideEffectHandler
import io.ipoli.android.friends.feed.sideeffect.PostSideEffectHandler
import io.ipoli.android.friends.invite.FirebaseInviteLinkBuilder
import io.ipoli.android.friends.invite.InviteLinkBuilder
import io.ipoli.android.friends.invite.sideeffect.AcceptFriendshipSideEffectHandler
import io.ipoli.android.friends.invite.sideeffect.InviteFriendsSideEffectHandler
import io.ipoli.android.friends.job.AddPostScheduler
import io.ipoli.android.friends.job.AndroidAddPostScheduler
import io.ipoli.android.friends.middleware.CreatePostsMiddleware
import io.ipoli.android.friends.persistence.FirestoreFriendRepository
import io.ipoli.android.friends.persistence.FriendRepository
import io.ipoli.android.friends.usecase.CreateReactionHistoryItemsUseCase
import io.ipoli.android.friends.usecase.SavePostReactionUseCase
import io.ipoli.android.friends.usecase.SavePostsUseCase
import io.ipoli.android.growth.persistence.AndroidAppUsageStatRepository
import io.ipoli.android.growth.persistence.AppUsageStatRepository
import io.ipoli.android.growth.sideeffect.GrowthSideEffectHandler
import io.ipoli.android.growth.usecase.CalculateGrowthStatsUseCase
import io.ipoli.android.habit.persistence.HabitRepository
import io.ipoli.android.habit.persistence.RoomHabitRepository
import io.ipoli.android.habit.sideeffect.HabitSideEffectHandler
import io.ipoli.android.habit.usecase.*
import io.ipoli.android.note.usecase.SaveQuestNoteUseCase
import io.ipoli.android.pet.PetDialogSideEffectHandler
import io.ipoli.android.pet.sideeffect.PetSideEffectHandler
import io.ipoli.android.pet.usecase.*
import io.ipoli.android.planday.data.AndroidWeatherRepository
import io.ipoli.android.planday.data.WeatherRepository
import io.ipoli.android.planday.job.AndroidPlanDayScheduler
import io.ipoli.android.planday.job.PlanDayScheduler
import io.ipoli.android.planday.persistence.FirestoreMotivationalImageRepository
import io.ipoli.android.planday.persistence.FirestoreQuoteRepository
import io.ipoli.android.planday.persistence.MotivationalImageRepository
import io.ipoli.android.planday.persistence.QuoteRepository
import io.ipoli.android.planday.sideeffect.PlanDaySideEffectHandler
import io.ipoli.android.planday.usecase.CalculateAwesomenessScoreUseCase
import io.ipoli.android.planday.usecase.CalculateFocusDurationUseCase
import io.ipoli.android.player.AndroidLevelDownScheduler
import io.ipoli.android.player.AndroidLevelUpScheduler
import io.ipoli.android.player.LevelDownScheduler
import io.ipoli.android.player.LevelUpScheduler
import io.ipoli.android.player.attribute.usecase.AddTagToAttributeUseCase
import io.ipoli.android.player.attribute.usecase.CheckForOneTimeBoostUseCase
import io.ipoli.android.player.attribute.usecase.RemoveTagFromAttributeUseCase
import io.ipoli.android.player.auth.saga.AuthSideEffectHandler
import io.ipoli.android.player.job.AndroidSecretSocietyInviteScheduler
import io.ipoli.android.player.job.SecretSocietyInviteScheduler
import io.ipoli.android.player.persistence.AndroidPlayerRepository
import io.ipoli.android.player.persistence.PlayerRepository
import io.ipoli.android.player.sideeffect.ProfileSideEffectHandler
import io.ipoli.android.player.usecase.*
import io.ipoli.android.player.view.PlayerSideEffectHandler
import io.ipoli.android.quest.bucketlist.sideeffect.BucketListSideEffectHandler
import io.ipoli.android.quest.bucketlist.usecase.CreateBucketListItemsUseCase
import io.ipoli.android.quest.data.persistence.QuestRepository
import io.ipoli.android.quest.data.persistence.RoomQuestRepository
import io.ipoli.android.quest.edit.sideeffect.EditQuestSideEffectHandler
import io.ipoli.android.quest.job.AndroidJobReminderScheduler
import io.ipoli.android.quest.job.AndroidJobRewardScheduler
import io.ipoli.android.quest.job.ReminderScheduler
import io.ipoli.android.quest.job.RewardScheduler
import io.ipoli.android.quest.reminder.formatter.TimeUnitFormatter
import io.ipoli.android.quest.schedule.agenda.sideeffect.AgendaSideEffectHandler
import io.ipoli.android.quest.schedule.agenda.usecase.CreateAgendaItemsUseCase
import io.ipoli.android.quest.schedule.agenda.usecase.CreateAgendaPreviewItemsUseCase
import io.ipoli.android.quest.schedule.agenda.usecase.FindAgendaDatesUseCase
import io.ipoli.android.quest.schedule.calendar.sideeffect.DayViewSideEffectHandler
import io.ipoli.android.quest.schedule.summary.sideeffect.ScheduleSummarySideEffectHandler
import io.ipoli.android.quest.schedule.summary.usecase.CreateScheduleSummaryItemsUseCase
import io.ipoli.android.quest.schedule.today.persistence.AndroidTodayImageRepository
import io.ipoli.android.quest.schedule.today.persistence.TodayImageRepository
import io.ipoli.android.quest.schedule.today.usecase.CreateTodayItemsUseCase
import io.ipoli.android.quest.show.job.AndroidJobTimerCompleteScheduler
import io.ipoli.android.quest.show.job.TimerCompleteScheduler
import io.ipoli.android.quest.show.sideeffect.QuestSideEffectHandler
import io.ipoli.android.quest.show.usecase.*
import io.ipoli.android.quest.subquest.usecase.*
import io.ipoli.android.quest.usecase.*
import io.ipoli.android.repeatingquest.AndroidSaveQuestsForRepeatingQuestScheduler
import io.ipoli.android.repeatingquest.SaveQuestsForRepeatingQuestScheduler
import io.ipoli.android.repeatingquest.persistence.RepeatingQuestRepository
import io.ipoli.android.repeatingquest.persistence.RoomRepeatingQuestRepository
import io.ipoli.android.repeatingquest.sideeffect.RepeatingQuestSideEffectHandler
import io.ipoli.android.repeatingquest.usecase.*
import io.ipoli.android.settings.sideeffect.SettingsSideEffectHandler
import io.ipoli.android.settings.usecase.*
import io.ipoli.android.store.avatar.sideeffect.AvatarSideEffectHandler
import io.ipoli.android.store.avatar.usecase.BuyAvatarUseCase
import io.ipoli.android.store.avatar.usecase.ChangeAvatarUseCase
import io.ipoli.android.store.gem.GemPackSideEffectHandler
import io.ipoli.android.store.membership.MembershipSideEffectHandler
import io.ipoli.android.store.membership.job.AndroidCheckMembershipStatusScheduler
import io.ipoli.android.store.membership.job.CheckMembershipStatusScheduler
import io.ipoli.android.store.membership.usecase.CalculateMembershipPlanPriceUseCase
import io.ipoli.android.store.membership.usecase.RemoveMembershipUseCase
import io.ipoli.android.store.membership.usecase.UpdatePlayerMembershipUseCase
import io.ipoli.android.store.powerup.job.AndroidRemoveExpiredPowerUpsScheduler
import io.ipoli.android.store.powerup.job.RemoveExpiredPowerUpsScheduler
import io.ipoli.android.store.powerup.middleware.CheckEnabledPowerUpMiddleWare
import io.ipoli.android.store.powerup.sideeffect.PowerUpSideEffectHandler
import io.ipoli.android.store.powerup.usecase.BuyPowerUpUseCase
import io.ipoli.android.store.powerup.usecase.EnableAllPowerUpsUseCase
import io.ipoli.android.store.powerup.usecase.RemoveExpiredPowerUpsUseCase
import io.ipoli.android.store.sideeffect.StoreSideEffectHandler
import io.ipoli.android.store.theme.sideeffect.ThemeSideEffectHandler
import io.ipoli.android.store.theme.usecase.BuyThemeUseCase
import io.ipoli.android.store.theme.usecase.ChangeThemeUseCase
import io.ipoli.android.store.usecase.PurchaseGemPackUseCase
import io.ipoli.android.tag.persistence.RoomTagRepository
import io.ipoli.android.tag.persistence.TagRepository
import io.ipoli.android.tag.sideeffect.TagSideEffectHandler
import io.ipoli.android.tag.usecase.*
import space.traversal.kapsule.HasModules
import space.traversal.kapsule.Injects
import space.traversal.kapsule.required
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Venelin Valkov <venelin@mypoli.fun>
 * on 9/10/17.
 */

interface AndroidModule {
    val layoutInflater: LayoutInflater

    val sharedPreferences: SharedPreferences

    val timeUnitFormatter: TimeUnitFormatter

    val calendarFormatter: CalendarFormatter

    val eventLogger: EventLogger

    val imageLoader: ImageLoader

    val dataImporter: DataImporter

    val dataExporter: DataExporter

    val firestoreToLocalPlayerMigrator: FirestoreToLocalPlayerMigrator

    val internetConnectionChecker: InternetConnectionChecker

    val billingResponseHandler: BillingResponseHandler

    val billingRequestExecutor: BillingRequestExecutor

    val inviteLinkBuilder: InviteLinkBuilder

    val notificationManager: NotificationManager
}

class MainAndroidModule(
    private val context: Context
) : AndroidModule {
    override val layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

    override val sharedPreferences: SharedPreferences
        get() =
            PreferenceManager.getDefaultSharedPreferences(
                context
            )

    override val timeUnitFormatter get() = TimeUnitFormatter(context)

    override val calendarFormatter get() = CalendarFormatter(context)

    override val dataImporter get() = DataImporter(context)

    override val dataExporter get() = DataExporter(context)

    override val firestoreToLocalPlayerMigrator get() = FirestoreToLocalPlayerMigrator(context)

    override val eventLogger
        get() = SimpleEventLogger(
            amplitude = Amplitude.getInstance()
        )

    override val imageLoader get() = AndroidImageLoader()

    override val internetConnectionChecker get() = InternetConnectionChecker(context)

    override val billingResponseHandler
        get() = BillingResponseHandler(eventLogger)

    override val billingRequestExecutor
        get() = BillingRequestExecutor(billingResponseHandler)

    override val inviteLinkBuilder
        get() = FirebaseInviteLinkBuilder()


    override val notificationManager: NotificationManager
        get() = AndroidNotificationManager(context)
}

interface UseCaseModule {

    val executorService: ExecutorService

    val permissionChecker: PermissionChecker

    val remoteDatabase: FirebaseFirestore

    val localDatabase: MyPoliRoomDatabase

    val questRepository: QuestRepository
    val playerRepository: PlayerRepository
    val repeatingQuestRepository: RepeatingQuestRepository
    val challengeRepository: ChallengeRepository
    val eventRepository: EventRepository
    val calendarRepository: CalendarRepository
    val tagRepository: TagRepository
    val weatherRepository: WeatherRepository
    val motivationalImageRepository: MotivationalImageRepository
    val quoteRepository: QuoteRepository
    val dailyChallengeRepository: DailyChallengeRepository
    val appUsageStatRepository: AppUsageStatRepository
    val habitRepository: HabitRepository
    val entityReminderRepository: EntityReminderRepository
    val postRepository: PostRepository
    val friendRepository: FriendRepository
    val presetChallengeRepository: PresetChallengeRepository
    val imageRepository: ImageRepository
    val todayImageRepository: TodayImageRepository

    val reminderScheduler: ReminderScheduler
    val timerCompleteScheduler: TimerCompleteScheduler
    val rewardScheduler: RewardScheduler
    val levelUpScheduler: LevelUpScheduler
    val levelDownScheduler: LevelDownScheduler
    val saveQuestsForRepeatingQuestScheduler: SaveQuestsForRepeatingQuestScheduler
    val removeExpiredPowerUpsScheduler: RemoveExpiredPowerUpsScheduler
    val checkMembershipStatusScheduler: CheckMembershipStatusScheduler
    val ratePopupScheduler: RatePopupScheduler
    val planDayScheduler: PlanDayScheduler
    val dailyChallengeCompleteScheduler: DailyChallengeCompleteScheduler
    val updateAchievementProgressScheduler: UpdateAchievementProgressScheduler
    val showUnlockedAchievementsScheduler: ShowUnlockedAchievementsScheduler
    val resetDayScheduler: ResetDayScheduler
    val secretSocietyInviteScheduler: SecretSocietyInviteScheduler
    val addPostScheduler: AddPostScheduler

    val loadScheduleForDateUseCase: LoadScheduleForDateUseCase
    val saveQuestUseCase: SaveQuestUseCase
    val removeQuestUseCase: RemoveQuestUseCase
    val undoRemoveQuestUseCase: UndoRemovedQuestUseCase
    val findQuestsToRemindUseCase: FindQuestsToRemindUseCase
    val snoozeQuestUseCase: SnoozeQuestUseCase
    val completeQuestUseCase: CompleteQuestUseCase
    val undoCompletedQuestUseCase: UndoCompletedQuestUseCase
    val rewardPlayerUseCase: RewardPlayerUseCase
    val removeRewardFromPlayerUseCase: RemoveRewardFromPlayerUseCase
    val feedPetUseCase: FeedPetUseCase
    val revivePetUseCase: RevivePetUseCase
    val buyPetUseCase: BuyPetUseCase
    val changePetUseCase: ChangePetUseCase
    val findPetUseCase: FindPetUseCase
    val changeThemeUseCase: ChangeThemeUseCase
    val buyThemeUseCase: BuyThemeUseCase
    val renamePetUseCase: RenamePetUseCase
    val buyIconPackUseCase: BuyIconPackUseCase
    val buyColorPackUseCase: BuyColorPackUseCase
    val convertCoinsToGemsUseCase: ConvertCoinsToGemsUseCase
    val comparePetItemsUseCase: ComparePetItemsUseCase
    val buyPetItemUseCase: BuyPetItemUseCase
    val equipPetItemUseCase: EquipPetItemUseCase
    val takeOffPetItemUseCase: TakeOffPetItemUseCase
    val purchaseGemPackUseCase: PurchaseGemPackUseCase
    val unlockPresetChallengeUseCase: UnlockPresetChallengeUseCase
    val splitDurationForPomodoroTimerUseCase: SplitDurationForPomodoroTimerUseCase
    val findPlayerLevelUseCase: FindPlayerLevelUseCase
    val lowerPlayerStatsUseCase: LowerPlayerStatsUseCase
    val completeTimeRangeUseCase: CompleteTimeRangeUseCase
    val cancelTimerUseCase: CancelTimerUseCase
    val addPomodoroUseCase: AddPomodoroUseCase
    val removePomodoroUseCase: RemovePomodoroUseCase
    val addTimerToQuestUseCase: AddTimerToQuestUseCase
    val findAgendaDatesUseCase: FindAgendaDatesUseCase
    val createAgendaItemsUseCase: CreateAgendaItemsUseCase
    val saveRepeatingQuestUseCase: SaveRepeatingQuestUseCase
    val findNextDateForRepeatingQuestUseCase: FindNextDateForRepeatingQuestUseCase
    val findPeriodProgressForRepeatingQuestUseCase: FindPeriodProgressForRepeatingQuestUseCase
    val saveQuestsForRepeatingQuestUseCase: SaveQuestsForRepeatingQuestUseCase
    val removeRepeatingQuestUseCase: RemoveRepeatingQuestUseCase
    val createRepeatingQuestHistoryUseCase: CreateRepeatingQuestHistoryUseCase
    val createPlaceholderQuestsForRepeatingQuestsUseCase: CreatePlaceholderQuestsForRepeatingQuestsUseCase
    val saveChallengeUseCase: SaveChallengeUseCase
    val saveQuestsForChallengeUseCase: SaveQuestsForChallengeUseCase
    val removeQuestFromChallengeUseCase: RemoveQuestFromChallengeUseCase
    val removeHabitFromChallengeUseCase: RemoveHabitFromChallengeUseCase
    val removeChallengeUseCase: RemoveChallengeUseCase
    val loadQuestPickerQuestsUseCase: LoadQuestPickerQuestsUseCase
    val findQuestsForChallengeUseCase: FindQuestsForChallengeUseCase
    val findHabitsForChallengeUseCase: FindHabitsForChallengeUseCase
    val findNextDateForChallengeUseCase: FindNextDateForChallengeUseCase
    val findChallengeProgressUseCase: FindChallengeProgressUseCase
    val completeChallengeUseCase: CompleteChallengeUseCase
    val buyPowerUpUseCase: BuyPowerUpUseCase
    val removeExpiredPowerUpsUseCase: RemoveExpiredPowerUpsUseCase
    val enableAllPowerUpsUseCase: EnableAllPowerUpsUseCase
    val updatePlayerMembershipUseCase: UpdatePlayerMembershipUseCase
    val calculateMembershipPlanPriceUseCase: CalculateMembershipPlanPriceUseCase
    val removeMembershipUseCase: RemoveMembershipUseCase
    val buyAvatarUseCase: BuyAvatarUseCase
    val changeAvatarUseCase: ChangeAvatarUseCase
    val completeSubQuestUseCase: CompleteSubQuestUseCase
    val undoCompletedSubQuestUseCase: UndoCompletedSubQuestUseCase
    val saveSubQuestNameUseCase: SaveSubQuestNameUseCase
    val addSubQuestUseCase: AddSubQuestUseCase
    val removeSubQuestUseCase: RemoveSubQuestUseCase
    val reorderSubQuestUseCase: ReorderSubQuestUseCase
    val saveQuestNoteUseCase: SaveQuestNoteUseCase
    val findEventsBetweenDatesUseCase: FindEventsBetweenDatesUseCase
    val saveSyncCalendarsUseCase: SaveSyncCalendarsUseCase
    val saveTagUseCase: SaveTagUseCase
    val favoriteTagUseCase: FavoriteTagUseCase
    val unfavoriteTagUseCase: UnfavoriteTagUseCase
    val createTagItemsUseCase: CreateTagItemsUseCase
    val addQuestCountToTagUseCase: AddQuestCountToTagUseCase
    val removeTagUseCase: RemoveTagUseCase
    val createBucketListItemsUseCase: CreateBucketListItemsUseCase
    val rescheduleQuestUseCase: RescheduleQuestUseCase
    val calculateAwesomenessScoreUseCase: CalculateAwesomenessScoreUseCase
    val calculateFocusDurationUseCase: CalculateFocusDurationUseCase
    val savePlanDayTimeUseCase: SavePlanDayTimeUseCase
    val savePlanDaysUseCase: SavePlanDaysUseCase
    val saveTimeFormatUseCase: SaveTimeFormatUseCase
    val saveTemperatureUnitUseCase: SaveTemperatureUnitUseCase
    val saveReminderNotificationStyleUseCase: SaveReminderNotificationStyleUseCase
    val savePlanDayNotificationStyleUseCase: SavePlanDayNotificationStyleUseCase
    val checkForDailyChallengeCompletionUseCase: CheckForDailyChallengeCompletionUseCase
    val checkDailyChallengeProgressUseCase: CheckDailyChallengeProgressUseCase
    val loadDailyChallengeUseCase: LoadDailyChallengeUseCase
    val saveDailyChallengeQuestIdsUseCase: SaveDailyChallengeQuestIdsUseCase
    val calculateGrowthStatsUseCase: CalculateGrowthStatsUseCase
    val findAverageFocusedDurationForPeriodUseCase: FindAverageFocusedDurationForPeriodUseCase
    val saveQuickDoNotificationSettingUseCase: SaveQuickDoNotificationSettingUseCase
    val saveAutoPostingSettingUseCase: SaveAutoPostSettingUseCase
    val saveProfileUseCase: SaveProfileUseCase
    val unlockAchievementsUseCase: UnlockAchievementsUseCase
    val updateAchievementProgressUseCase: UpdateAchievementProgressUseCase
    val createAchievementItemsUseCase: CreateAchievementItemsUseCase
    val saveHabitUseCase: SaveHabitUseCase
    val completeHabitUseCase: CompleteHabitUseCase
    val undoCompleteHabitUseCase: UndoCompleteHabitUseCase
    val removeHabitUseCase: RemoveHabitUseCase
    val createHabitItemsUseCase: CreateHabitItemsUseCase
    val createScheduleSummaryItemsUseCase: CreateScheduleSummaryItemsUseCase
    val savePostsUseCase: SavePostsUseCase
    val savePostReactionUseCase: SavePostReactionUseCase
    val createReactionHistoryItemsUseCase: CreateReactionHistoryItemsUseCase
    val logDataUseCase: LogDataUseCase
    val saveResetDayTimeUseCase: SaveResetDayTimeUseCase
    val createTodayItemsUseCase: CreateTodayItemsUseCase
    val updatePlayerStatsUseCase: UpdatePlayerStatsUseCase
    val checkForOneTimeBoostUseCase: CheckForOneTimeBoostUseCase
    val addTagToAttributeUseCase: AddTagToAttributeUseCase
    val removeTagFromAttributeUseCase: RemoveTagFromAttributeUseCase
    val createChallengeFromPresetUseCase: CreateChallengeFromPresetUseCase
    val createHabitHistoryItemsUseCase: CreateHabitHistoryItemsUseCase
    val createChallengeProgressItemsUseCase: CreateChallengeProgressItemsUseCase
    val createAgendaPreviewItemsUseCase : CreateAgendaPreviewItemsUseCase
}

class MainUseCaseModule(private val context: Context) : UseCaseModule {

    override val executorService: ExecutorService = Executors.newCachedThreadPool()

    override val permissionChecker
        get() = AndroidPermissionChecker(context)

    override val remoteDatabase = FirebaseFirestore.getInstance().also {
        it.firestoreSettings =
            FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build()
    }

    override val localDatabase get() = MyPoliRoomDatabase.getInstance(context)

    override val questRepository =
        RoomQuestRepository(
            dao = localDatabase.questDao(),
            entityReminderDao = localDatabase.entityReminderDao(),
            tagDao = localDatabase.tagDao(),
            remoteDatabase = remoteDatabase
        )

    override val playerRepository =
        AndroidPlayerRepository(
            database = remoteDatabase,
            dao = localDatabase.playerDao(),
            tagDao = localDatabase.tagDao()
        )

    override val repeatingQuestRepository =
        RoomRepeatingQuestRepository(
            dao = localDatabase.repeatingQuestDao(),
            tagDao = localDatabase.tagDao()
        )

    override val challengeRepository =
        RoomChallengeRepository(
            dao = localDatabase.challengeDao(),
            tagDao = localDatabase.tagDao(),
            remoteDatabase = remoteDatabase
        )

    override val eventRepository =
        AndroidCalendarEventRepository()

    override val calendarRepository =
        AndroidCalendarRepository()

    override val tagRepository =
        RoomTagRepository(localDatabase)

    override val weatherRepository =
        AndroidWeatherRepository(context)

    override val motivationalImageRepository =
        FirestoreMotivationalImageRepository(
            remoteDatabase
        )

    override val quoteRepository =
        FirestoreQuoteRepository(remoteDatabase)

    override val dailyChallengeRepository =
        RoomDailyChallengeRepository(localDatabase.dailyChallengeDao())

    override val appUsageStatRepository =
        AndroidAppUsageStatRepository(context)

    override val habitRepository =
        RoomHabitRepository(
            dao = localDatabase.habitDao(),
            tagDao = localDatabase.tagDao()
        )

    override val entityReminderRepository =
        RoomEntityReminderRepository(
            localDatabase.entityReminderDao()
        )

    override val postRepository =
        AndroidPostRepository(remoteDatabase, executorService)

    override val friendRepository = FirestoreFriendRepository(remoteDatabase)

    override val presetChallengeRepository = FirestorePresetChallengeRepository(remoteDatabase)

    override val imageRepository = FirebaseStorageImageRepository()

    override val todayImageRepository
        get() = AndroidTodayImageRepository(
            PreferenceManager.getDefaultSharedPreferences(
                context
            )
        )

    override val reminderScheduler get() = AndroidJobReminderScheduler(context)

    override val timerCompleteScheduler get() = AndroidJobTimerCompleteScheduler()

    override val rewardScheduler get() = AndroidJobRewardScheduler(context)

    override val levelUpScheduler get() = AndroidLevelUpScheduler()

    override val levelDownScheduler get() = AndroidLevelDownScheduler()

    override val saveQuestsForRepeatingQuestScheduler get() = AndroidSaveQuestsForRepeatingQuestScheduler()

    override val removeExpiredPowerUpsScheduler get() = AndroidRemoveExpiredPowerUpsScheduler()

    override val checkMembershipStatusScheduler
        get() = AndroidCheckMembershipStatusScheduler()

    override val ratePopupScheduler get() = AndroidRatePopupScheduler()

    override val dailyChallengeCompleteScheduler
        get() = AndroidDailyChallengeCompleteScheduler()

    override val updateAchievementProgressScheduler
        get() = AndroidUpdateAchievementProgressScheduler()

    override val showUnlockedAchievementsScheduler
        get() = AndroidShowUnlockedAchievementsScheduler(context)

    override val planDayScheduler
        get() = AndroidPlanDayScheduler(context)

    override val loadScheduleForDateUseCase
        get() = LoadScheduleForDateUseCase()

    override val resetDayScheduler
        get() = AndroidResetDayScheduler(context)

    override val secretSocietyInviteScheduler
        get() = AndroidSecretSocietyInviteScheduler()

    override val addPostScheduler
        get() = AndroidAddPostScheduler()

    override val saveQuestUseCase
        get() = SaveQuestUseCase(
            questRepository,
            reminderScheduler
        )
    override val removeQuestUseCase
        get() = RemoveQuestUseCase(
            questRepository,
            timerCompleteScheduler,
            reminderScheduler
        )
    override val undoRemoveQuestUseCase
        get() = UndoRemovedQuestUseCase(
            questRepository,
            reminderScheduler
        )
    override val findQuestsToRemindUseCase get() = FindQuestsToRemindUseCase(questRepository)
    override val snoozeQuestUseCase get() = SnoozeQuestUseCase(questRepository, reminderScheduler)
    override val completeQuestUseCase
        get() = CompleteQuestUseCase(
            questRepository,
            playerRepository,
            reminderScheduler,
            rewardScheduler,
            ratePopupScheduler,
            rewardPlayerUseCase,
            checkForDailyChallengeCompletionUseCase,
            challengeRepository,
            addPostScheduler,
            postRepository
        )
    override val undoCompletedQuestUseCase
        get() = UndoCompletedQuestUseCase(
            questRepository,
            reminderScheduler,
            removeRewardFromPlayerUseCase
        )
    override val rewardPlayerUseCase
        get() = RewardPlayerUseCase(
            playerRepository,
            levelUpScheduler,
            unlockAchievementsUseCase,
            checkForOneTimeBoostUseCase,
            removeRewardFromPlayerUseCase
        )
    override val removeRewardFromPlayerUseCase
        get() = RemoveRewardFromPlayerUseCase(
            playerRepository,
            levelDownScheduler,
            unlockAchievementsUseCase
        )
    override val feedPetUseCase get() = FeedPetUseCase(playerRepository)
    override val revivePetUseCase get() = RevivePetUseCase(playerRepository)
    override val buyPetUseCase get() = BuyPetUseCase(playerRepository)
    override val changePetUseCase get() = ChangePetUseCase(playerRepository)
    override val findPetUseCase get() = FindPetUseCase(playerRepository)
    override val changeThemeUseCase get() = ChangeThemeUseCase(playerRepository)
    override val buyThemeUseCase get() = BuyThemeUseCase(playerRepository)
    override val renamePetUseCase get() = RenamePetUseCase(playerRepository)
    override val buyIconPackUseCase get() = BuyIconPackUseCase(playerRepository)
    override val buyColorPackUseCase get() = BuyColorPackUseCase(playerRepository)
    override val convertCoinsToGemsUseCase get() = ConvertCoinsToGemsUseCase(playerRepository)
    override val comparePetItemsUseCase get() = ComparePetItemsUseCase()
    override val buyPetItemUseCase get() = BuyPetItemUseCase(playerRepository)
    override val equipPetItemUseCase get() = EquipPetItemUseCase(playerRepository)
    override val takeOffPetItemUseCase get() = TakeOffPetItemUseCase(playerRepository)
    override val purchaseGemPackUseCase get() = PurchaseGemPackUseCase(playerRepository)
    override val unlockPresetChallengeUseCase get() = UnlockPresetChallengeUseCase(playerRepository)
    override val splitDurationForPomodoroTimerUseCase get() = SplitDurationForPomodoroTimerUseCase()
    override val completeTimeRangeUseCase
        get() = CompleteTimeRangeUseCase(
            questRepository,
            splitDurationForPomodoroTimerUseCase,
            completeQuestUseCase,
            timerCompleteScheduler
        )
    override val cancelTimerUseCase
        get() = CancelTimerUseCase(
            questRepository,
            reminderScheduler,
            timerCompleteScheduler
        )
    override val addPomodoroUseCase
        get() = AddPomodoroUseCase(
            questRepository,
            splitDurationForPomodoroTimerUseCase
        )
    override val removePomodoroUseCase
        get() = RemovePomodoroUseCase(
            questRepository,
            splitDurationForPomodoroTimerUseCase
        )
    override val addTimerToQuestUseCase: AddTimerToQuestUseCase
        get() = AddTimerToQuestUseCase(
            questRepository,
            cancelTimerUseCase,
            reminderScheduler,
            timerCompleteScheduler
        )

    override val findAgendaDatesUseCase get() = FindAgendaDatesUseCase(questRepository)
    override val createAgendaItemsUseCase get() = CreateAgendaItemsUseCase()

    override val findPlayerLevelUseCase
        get() = FindPlayerLevelUseCase(playerRepository)

    override val lowerPlayerStatsUseCase
        get() = LowerPlayerStatsUseCase(
            questRepository,
            playerRepository
        )

    override val saveRepeatingQuestUseCase
        get() = SaveRepeatingQuestUseCase(
            questRepository,
            repeatingQuestRepository,
            saveQuestsForRepeatingQuestUseCase,
            reminderScheduler
        )

    override val findNextDateForRepeatingQuestUseCase
        get() = FindNextDateForRepeatingQuestUseCase(
            questRepository
        )

    override val findPeriodProgressForRepeatingQuestUseCase
        get() = FindPeriodProgressForRepeatingQuestUseCase(
            questRepository
        )

    override val saveQuestsForRepeatingQuestUseCase
        get() = SaveQuestsForRepeatingQuestUseCase(
            questRepository,
            reminderScheduler
        )
    override val removeRepeatingQuestUseCase
        get() = RemoveRepeatingQuestUseCase(
            questRepository,
            repeatingQuestRepository,
            reminderScheduler
        )
    override val createRepeatingQuestHistoryUseCase
        get() = CreateRepeatingQuestHistoryUseCase(
            questRepository,
            repeatingQuestRepository
        )
    override val createPlaceholderQuestsForRepeatingQuestsUseCase
        get() = CreatePlaceholderQuestsForRepeatingQuestsUseCase(
            questRepository,
            repeatingQuestRepository
        )

    override val saveQuestsForChallengeUseCase
        get() = SaveQuestsForChallengeUseCase(
            questRepository,
            repeatingQuestRepository,
            saveRepeatingQuestUseCase
        )

    override val removeQuestFromChallengeUseCase
        get() = RemoveQuestFromChallengeUseCase(questRepository, repeatingQuestRepository)

    override val removeHabitFromChallengeUseCase
        get() = RemoveHabitFromChallengeUseCase(habitRepository)

    override val saveChallengeUseCase
        get() = SaveChallengeUseCase(
            challengeRepository,
            saveQuestsForChallengeUseCase,
            habitRepository
        )

    override val removeChallengeUseCase
        get() = RemoveChallengeUseCase(
            challengeRepository,
            questRepository,
            repeatingQuestRepository,
            habitRepository,
            reminderScheduler
        )

    override val loadQuestPickerQuestsUseCase
        get() = LoadQuestPickerQuestsUseCase(
            questRepository,
            repeatingQuestRepository
        )

    override val findQuestsForChallengeUseCase
        get() = FindQuestsForChallengeUseCase(
            questRepository,
            repeatingQuestRepository
        )

    override val findHabitsForChallengeUseCase
        get() = FindHabitsForChallengeUseCase(habitRepository)

    override val findNextDateForChallengeUseCase
        get() = FindNextDateForChallengeUseCase(questRepository)

    override val findChallengeProgressUseCase
        get() = FindChallengeProgressUseCase()

    override val completeChallengeUseCase
        get() = CompleteChallengeUseCase(
            challengeRepository,
            rewardPlayerUseCase,
            playerRepository
        )

    override val buyPowerUpUseCase
        get() = BuyPowerUpUseCase(playerRepository)

    override val removeExpiredPowerUpsUseCase
        get() = RemoveExpiredPowerUpsUseCase(playerRepository)

    override val enableAllPowerUpsUseCase
        get() = EnableAllPowerUpsUseCase(playerRepository)

    override val updatePlayerMembershipUseCase
        get() = UpdatePlayerMembershipUseCase(
            playerRepository,
            enableAllPowerUpsUseCase
        )

    override val calculateMembershipPlanPriceUseCase
        get() = CalculateMembershipPlanPriceUseCase()

    override val removeMembershipUseCase
        get() = RemoveMembershipUseCase(playerRepository)

    override val buyAvatarUseCase
        get() = BuyAvatarUseCase(playerRepository)

    override val changeAvatarUseCase
        get() = ChangeAvatarUseCase(playerRepository)

    override val completeSubQuestUseCase
        get() = CompleteSubQuestUseCase(questRepository)

    override val undoCompletedSubQuestUseCase
        get() = UndoCompletedSubQuestUseCase(questRepository)

    override val saveSubQuestNameUseCase
        get() = SaveSubQuestNameUseCase(questRepository)

    override val addSubQuestUseCase
        get() = AddSubQuestUseCase(questRepository)

    override val removeSubQuestUseCase
        get() = RemoveSubQuestUseCase(questRepository)

    override val reorderSubQuestUseCase
        get() = ReorderSubQuestUseCase(questRepository)

    override val saveQuestNoteUseCase
        get() = SaveQuestNoteUseCase(questRepository)

    override val findEventsBetweenDatesUseCase: FindEventsBetweenDatesUseCase
        get() = FindEventsBetweenDatesUseCase(playerRepository, eventRepository, permissionChecker)

    override val saveSyncCalendarsUseCase
        get() = SaveSyncCalendarsUseCase(playerRepository)

    override val saveTagUseCase
        get() = SaveTagUseCase(tagRepository)

    override val favoriteTagUseCase
        get() = FavoriteTagUseCase(tagRepository, saveTagUseCase)

    override val unfavoriteTagUseCase
        get() = UnfavoriteTagUseCase(tagRepository, saveTagUseCase)

    override val createTagItemsUseCase
        get() = CreateTagItemsUseCase()

    override val addQuestCountToTagUseCase
        get() = AddQuestCountToTagUseCase(questRepository)

    override val removeTagUseCase
        get() = RemoveTagUseCase(tagRepository, playerRepository)

    override val createBucketListItemsUseCase
        get() = CreateBucketListItemsUseCase()

    override val rescheduleQuestUseCase
        get() = RescheduleQuestUseCase(questRepository, reminderScheduler)

    override val calculateAwesomenessScoreUseCase
        get() = CalculateAwesomenessScoreUseCase(questRepository)

    override val calculateFocusDurationUseCase
        get() = CalculateFocusDurationUseCase(questRepository)

    override val savePlanDayTimeUseCase
        get() = SavePlanDayTimeUseCase(playerRepository, planDayScheduler)

    override val savePlanDaysUseCase
        get() = SavePlanDaysUseCase(playerRepository)

    override val saveTimeFormatUseCase
        get() = SaveTimeFormatUseCase(playerRepository)

    override val saveTemperatureUnitUseCase
        get() = SaveTemperatureUnitUseCase(playerRepository)

    override val savePlanDayNotificationStyleUseCase
        get() = SavePlanDayNotificationStyleUseCase(playerRepository)

    override val saveReminderNotificationStyleUseCase
        get() = SaveReminderNotificationStyleUseCase(playerRepository)

    override val checkForDailyChallengeCompletionUseCase
        get() = CheckForDailyChallengeCompletionUseCase(
            dailyChallengeRepository,
            questRepository,
            playerRepository,
            rewardPlayerUseCase,
            savePostsUseCase,
            dailyChallengeCompleteScheduler
        )

    override val checkDailyChallengeProgressUseCase
        get() = CheckDailyChallengeProgressUseCase(dailyChallengeRepository, questRepository)

    override val loadDailyChallengeUseCase
        get() = LoadDailyChallengeUseCase(dailyChallengeRepository)

    override val saveDailyChallengeQuestIdsUseCase
        get() = SaveDailyChallengeQuestIdsUseCase(dailyChallengeRepository)

    override val calculateGrowthStatsUseCase
        get() = CalculateGrowthStatsUseCase(
            calculateAwesomenessScoreUseCase,
            calculateFocusDurationUseCase,
            questRepository,
            appUsageStatRepository
        )

    override val findAverageFocusedDurationForPeriodUseCase
        get() = FindAverageFocusedDurationForPeriodUseCase(questRepository)

    override val saveQuickDoNotificationSettingUseCase
        get() = SaveQuickDoNotificationSettingUseCase(playerRepository)

    override val saveAutoPostingSettingUseCase
        get() = SaveAutoPostSettingUseCase(playerRepository)

    override val saveProfileUseCase
        get() = SaveProfileUseCase(playerRepository)

    override val updatePlayerStatsUseCase
        get() = UpdatePlayerStatsUseCase(playerRepository)

    override val unlockAchievementsUseCase
        get() = UnlockAchievementsUseCase(
            playerRepository,
            showUnlockedAchievementsScheduler,
            updatePlayerStatsUseCase,
            savePostsUseCase
        )

    override val updateAchievementProgressUseCase
        get() = UpdateAchievementProgressUseCase(
            playerRepository,
            calculateAwesomenessScoreUseCase,
            calculateFocusDurationUseCase,
            friendRepository
        )

    override val createAchievementItemsUseCase
        get() = CreateAchievementItemsUseCase()

    override val saveHabitUseCase
        get() = SaveHabitUseCase(
            habitRepository,
            playerRepository,
            rewardPlayerUseCase,
            removeRewardFromPlayerUseCase
        )

    override val completeHabitUseCase
        get() = CompleteHabitUseCase(
            habitRepository,
            playerRepository,
            rewardPlayerUseCase,
            rewardScheduler,
            challengeRepository,
            addPostScheduler,
            postRepository
        )

    override val undoCompleteHabitUseCase
        get() = UndoCompleteHabitUseCase(
            habitRepository,
            removeRewardFromPlayerUseCase
        )

    override val removeHabitUseCase
        get() = RemoveHabitUseCase(habitRepository)

    override val createHabitItemsUseCase
        get() = CreateHabitItemsUseCase()

    override val createScheduleSummaryItemsUseCase
        get() = CreateScheduleSummaryItemsUseCase(
            eventRepository,
            playerRepository,
            permissionChecker
        )

    override val savePostsUseCase
        get() = SavePostsUseCase(
            postRepository,
            playerRepository,
            challengeRepository,
            imageRepository
        )

    override val savePostReactionUseCase
        get() = SavePostReactionUseCase(postRepository)

    override val createReactionHistoryItemsUseCase
        get() = CreateReactionHistoryItemsUseCase(playerRepository, friendRepository)

    override val logDataUseCase
        get() = LogDataUseCase(challengeRepository)

    override val saveResetDayTimeUseCase
        get() = SaveResetDayTimeUseCase(playerRepository, resetDayScheduler)

    override val createTodayItemsUseCase
        get() = CreateTodayItemsUseCase()

    override val checkForOneTimeBoostUseCase
        get() = CheckForOneTimeBoostUseCase(secretSocietyInviteScheduler)

    override val addTagToAttributeUseCase
        get() = AddTagToAttributeUseCase(playerRepository)

    override val removeTagFromAttributeUseCase
        get() = RemoveTagFromAttributeUseCase(playerRepository)

    override val createChallengeFromPresetUseCase
        get() = CreateChallengeFromPresetUseCase(saveChallengeUseCase)

    override val createHabitHistoryItemsUseCase
        get() = CreateHabitHistoryItemsUseCase()

    override val createChallengeProgressItemsUseCase
        get() = CreateChallengeProgressItemsUseCase()

    override val createAgendaPreviewItemsUseCase
        get() = CreateAgendaPreviewItemsUseCase()
}

interface StateStoreModule {
    val stateStore: StateStore<AppState>
}

class AndroidStateStoreModule : StateStoreModule, Injects<UIModule> {

    override val stateStore by required {
        StateStore(
            initialState = AppState(
                data = mapOf(
                    "AppDataState" to AppDataReducer.defaultState()
                )
            ),
            reducers = setOf(
                AppDataReducer
            ),
            sideEffectHandlers = setOf(
                LoadAllDataSideEffectHandler,
                AuthSideEffectHandler,
                AgendaSideEffectHandler,
                ChangePetSideEffectHandler,
                BuyPetSideEffectHandler,
                DayViewSideEffectHandler,
                RepeatingQuestSideEffectHandler,
                ChallengeSideEffectHandler,
                CalendarSideEffectHandler,
                MembershipSideEffectHandler,
                PowerUpSideEffectHandler,
                QuestSideEffectHandler,
                EditQuestSideEffectHandler,
                AvatarSideEffectHandler,
                ThemeSideEffectHandler,
                TagSideEffectHandler,
                BucketListSideEffectHandler,
                GemPackSideEffectHandler,
                StoreSideEffectHandler,
                PetSideEffectHandler,
                PlanDaySideEffectHandler,
                SettingsSideEffectHandler,
                MigrationSideEffectHandler,
                DailyChallengeSideEffectHandler,
                GrowthSideEffectHandler,
                ProfileSideEffectHandler,
                AchievementListSideEffectHandler,
                HabitSideEffectHandler,
                PlayerSideEffectHandler,
                PetMessageSideEffectHandler,
                PetDialogSideEffectHandler,
                ScheduleSummarySideEffectHandler,
                InviteFriendsSideEffectHandler,
                AcceptFriendshipSideEffectHandler,
                FeedSideEffectHandler,
                PresetChallengeSideEffectHandler,
                PostSideEffectHandler
            ),
            sideEffectHandlerExecutor = CoroutineSideEffectHandlerExecutor(),
            middleware = listOf(
                LogEventsMiddleWare,
                CheckEnabledPowerUpMiddleWare,
                AchievementProgressMiddleWare,
                CreatePostsMiddleware
            )
        )
    }
}

class UIModule(
    androidModule: AndroidModule,
    useCaseModule: UseCaseModule,
    stateStoreModule: StateStoreModule
) : AndroidModule by androidModule,
    UseCaseModule by useCaseModule,
    StateStoreModule by stateStoreModule,
    HasModules {

    override val modules =
        setOf(
            androidModule,
            useCaseModule,
            stateStoreModule
        )
}

class BackgroundModule(
    androidModule: AndroidModule,
    useCaseModule: UseCaseModule
) :
    AndroidModule by androidModule,
    UseCaseModule by useCaseModule,
    HasModules {

    override val modules =
        setOf(
            androidModule,
            useCaseModule
        )
}