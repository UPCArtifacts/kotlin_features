package tech.ula.model.state

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.* // ktlint-disable no-wildcard-imports
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import tech.ula.model.daos.FilesystemDao
import tech.ula.model.daos.SessionDao
import tech.ula.model.entities.Asset
import tech.ula.model.entities.Filesystem
import tech.ula.model.entities.Session
import tech.ula.model.repositories.AssetRepository
import tech.ula.model.repositories.DownloadMetadata
import tech.ula.model.repositories.UlaDatabase
import tech.ula.utils.* // ktlint-disable no-wildcard-imports
import java.io.IOException
import kotlin.Exception

@RunWith(MockitoJUnitRunner::class)
class SessionStartupFsmTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mocks

    @Mock lateinit var mockUlaDatabase: UlaDatabase

    @Mock lateinit var mockSessionDao: SessionDao

    @Mock lateinit var mockFilesystemDao: FilesystemDao

    @Mock lateinit var mockAssetRepository: AssetRepository

    @Mock lateinit var mockAssetDownloader: AssetDownloader

    @Mock lateinit var mockFilesystemManager: FilesystemManager

    @Mock lateinit var mockStateObserver: Observer<SessionStartupState>

    @Mock lateinit var mockLogger: Logger

    @Mock lateinit var mockStorageCalculator: StorageCalculator

    private lateinit var activeSessionLiveData: MutableLiveData<List<Session>>

    private lateinit var sessionFsm: SessionStartupFsm

    // Test setup variables
    private val activeSession = Session(id = -1, name = "active", filesystemId = -1, active = true)
    private val inactiveSession = Session(id = -1, name = "inactive", filesystemId = -1, active = false)

    private val assetDownloadName = "arm-assets.tar.gz"
    private val lowVersionCode = "v0.0.0"
    private val highVersionCode = "v1.0.0"
    private val url = "https://test.com"
    private val assetType = "type"
    private val asset = Asset("asset", assetType)
    private val assetList = listOf(asset)
    private val downloadMetadata = listOf(DownloadMetadata(assetDownloadName, assetType, highVersionCode, url))

    private val filesystem = Filesystem(id = -1, distributionType = assetType)

    private val incorrectTransitionEvent = SessionSelected(inactiveSession)
    private val incorrectTransitionState = RetrievingAssetLists
    private val possibleEvents = listOf(
            SessionSelected(inactiveSession),
            RetrieveAssetLists(filesystem),
            GenerateDownloads(filesystem, assetList),
            DownloadAssets(downloadMetadata),
            AssetDownloadComplete(0),
            CopyDownloadsToLocalStorage,
            ExtractFilesystem(filesystem),
            VerifyFilesystemAssets(filesystem)
    )
    private val possibleStates = listOf(
            IncorrectSessionTransition(incorrectTransitionEvent, incorrectTransitionState),
            WaitingForSessionSelection,
            SingleSessionSupported,
            SessionIsRestartable(inactiveSession),
            SessionIsReadyForPreparation(inactiveSession, filesystem),
            RetrievingAssetLists,
            AssetListsRetrievalSucceeded(assetList),
            AssetListsRetrievalFailed,
            GeneratingDownloadRequirements,
            NoDownloadsRequired,
            DownloadsRequired(downloadMetadata, false),
            DownloadingAssets(0, 0),
            DownloadsHaveSucceeded,
            DownloadsHaveFailed(DownloadFailureLocalizationData(0)),
            CopyingFilesToLocalDirectories,
            LocalDirectoryCopySucceeded,
            LocalDirectoryCopyFailed,
            VerifyingFilesystemAssets,
            FilesystemAssetVerificationSucceeded,
            AssetsAreMissingFromSupportDirectories,
            FilesystemAssetCopyFailed,
            ExtractingFilesystem("test"),
            ExtractionHasCompletedSuccessfully,
            ExtractionFailed("reason")
    )

    @Before
    fun setup() {
        activeSessionLiveData = MutableLiveData()
        val filesystemLiveData = MutableLiveData<List<Filesystem>>().apply { postValue(listOf(filesystem)) }

        whenever(mockUlaDatabase.sessionDao()).thenReturn(mockSessionDao)
        whenever(mockSessionDao.findActiveSessions()).thenReturn(activeSessionLiveData)
        whenever(mockUlaDatabase.filesystemDao()).thenReturn(mockFilesystemDao)
        whenever(mockFilesystemDao.getAllFilesystems()).thenReturn(filesystemLiveData)

        sessionFsm = SessionStartupFsm(
                mockUlaDatabase,
                mockAssetRepository,
                mockFilesystemManager,
                mockAssetDownloader,
                mockStorageCalculator,
                mockLogger
        )
    }

    @After
    fun teardown() {
        filesystem.versionCodeUsed = ""
        activeSessionLiveData = MutableLiveData()
    }

    @Test
    fun `Only allows correct state transitions`() {
        sessionFsm.getState().observeForever(mockStateObserver)

        for (event in possibleEvents) {
            for (state in possibleStates) {
                if (state is WaitingForSessionSelection) {
                    // Test the branch for receiving downloads not enqueued by us
                    whenever(mockAssetDownloader.downloadIsForUserland(0L))
                            .thenReturn(false)
                } else {
                    whenever(mockAssetDownloader.downloadIsForUserland(0L))
                            .thenReturn(true)
                }
                sessionFsm.setState(state)
                val result = sessionFsm.transitionIsAcceptable(event)
                when {
                    event is SessionSelected && state is WaitingForSessionSelection -> assertTrue(result)
                    event is RetrieveAssetLists && state is SessionIsReadyForPreparation -> assertTrue(result)
                    event is GenerateDownloads && state is AssetListsRetrievalSucceeded -> assertTrue(result)
                    event is DownloadAssets && state is DownloadsRequired -> assertTrue(result)
                    event is AssetDownloadComplete && (state is DownloadingAssets || state is WaitingForSessionSelection) -> assertTrue(result)
                    event is SyncDownloadState -> assertTrue(result)
                    event is CopyDownloadsToLocalStorage && state is DownloadsHaveSucceeded -> assertTrue(result)
                    event is VerifyFilesystemAssets && (state is NoDownloadsRequired || state is LocalDirectoryCopySucceeded) -> assertTrue(result)

                    event is VerifyAvailableStorage && state is FilesystemAssetVerificationSucceeded -> assertTrue(result)
                    event is VerifyAvailableStorageComplete && (state is VerifyingSufficientStorage || state is LowAvailableStorage) -> assertTrue(result)
                    event is ExtractFilesystem && state is StorageVerificationCompletedSuccessfully -> assertTrue(result)
                    event is ResetSessionState -> assertTrue(result)
                    else -> assertFalse(result)
                }
            }
        }
    }

    @Test
    fun `AssetDownloadComplete events can be submitted at any time if they are not userland downloads`() {
        val downloadId = 0L
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetDownloader.handleDownloadComplete(downloadId))
                .thenReturn(NonUserlandDownloadFound)

        runBlocking { sessionFsm.submitEvent(AssetDownloadComplete(downloadId), this) }

        verify(mockStateObserver, times(1)).onChanged(WaitingForSessionSelection)
        verifyNoMoreInteractions(mockStateObserver)
    }

    @Test
    fun `Exits early if incorrect transition event submitted`() {
        val state = WaitingForSessionSelection
        sessionFsm.setState(state)
        sessionFsm.getState().observeForever(mockStateObserver)

        val event = RetrieveAssetLists(filesystem)
        runBlocking { sessionFsm.submitEvent(event, this) }

        verify(mockStateObserver, times(1)).onChanged(IncorrectSessionTransition(event, state))
        verify(mockStateObserver, times(2)).onChanged(any()) // Observes when registered and again on state emission
    }

    @Test
    fun `Initial state is WaitingForSessionSelection`() {
        sessionFsm.getState().observeForever(mockStateObserver)

        verify(mockStateObserver).onChanged(WaitingForSessionSelection)
    }

    @Test
    fun `State can be reset`() {
        sessionFsm.getState().observeForever(mockStateObserver)

        for (state in possibleStates) {
            sessionFsm.setState(state)
            runBlocking { sessionFsm.submitEvent(ResetSessionState, this) }
        }

        val numberOfStates = possibleStates.size
        // Will initially be WaitingForSessionSelection (+1), the test for that state (+1), and then reset for each
        verify(mockStateObserver, times(numberOfStates + 2)).onChanged(WaitingForSessionSelection)
    }

    @Test
    fun `State is SingleSessionSupported if active session is not selected one`() {
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)
        activeSessionLiveData.postValue(listOf(activeSession))

        val differentSession = Session(id = 0, name = "inactive", filesystemId = -1, active = false)

        runBlocking { sessionFsm.submitEvent(SessionSelected(differentSession), this) }

        verify(mockStateObserver).onChanged(SingleSessionSupported)
    }

    @Test
    fun `State is SessionIsRestartable if active session is selected one`() {
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)
        activeSessionLiveData.postValue(listOf(activeSession))

        runBlocking { sessionFsm.submitEvent(SessionSelected(activeSession), this) }

        verify(mockStateObserver).onChanged(SessionIsRestartable(activeSession))
    }

    @Test
    fun `State is SessionIsReadyForPreparation if there are no active sessions on selection`() {
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)
        activeSessionLiveData.postValue(listOf())

        runBlocking { sessionFsm.submitEvent(SessionSelected(inactiveSession), this) }

        verify(mockStateObserver).onChanged(SessionIsReadyForPreparation(inactiveSession, filesystem))
    }

    @Test
    fun `State is RetrievingAssetLists and then AssetListsRetrieved`() {
        sessionFsm.setState(SessionIsReadyForPreparation(inactiveSession, filesystem))
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking {
            whenever(mockAssetRepository.getAssetList(filesystem.distributionType))
                    .thenReturn(assetList)
            sessionFsm.submitEvent(RetrieveAssetLists(filesystem), this) }

        verify(mockStateObserver).onChanged(RetrievingAssetLists)
        verify(mockStateObserver).onChanged(AssetListsRetrievalSucceeded(assetList))
    }

    @Test
    fun `State is AssetListsRetrievalFailed if remote and cached assets cannot be fetched`() {
        sessionFsm.setState(SessionIsReadyForPreparation(inactiveSession, filesystem))
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking {
            whenever(mockAssetRepository.getAssetList(filesystem.distributionType))
                    .thenReturn(listOf())
            sessionFsm.submitEvent(RetrieveAssetLists(filesystem), this)
        }

        verify(mockStateObserver).onChanged(RetrievingAssetLists)
        verify(mockStateObserver).onChanged(AssetListsRetrievalFailed)
    }

    @Test
    fun `handleGenerateDownloads finds that filesystemNeedsExtraction is false if the filesystem is extracted`() {
        sessionFsm.setState(AssetListsRetrievalSucceeded(assetList))
        sessionFsm.getState().observeForever(mockStateObserver)

        val filesystemNeedsExtraction = false
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(true)

        runBlocking {
            whenever(mockAssetRepository.generateDownloadRequirements(filesystem, assetList, filesystemNeedsExtraction))
                    .thenReturn(listOf())

            sessionFsm.submitEvent(GenerateDownloads(filesystem, assetList), this)
        }

        verify(mockStateObserver).onChanged(GeneratingDownloadRequirements)
        verify(mockStateObserver).onChanged(NoDownloadsRequired)
        verifyBlocking(mockAssetRepository) { generateDownloadRequirements(filesystem, assetList, filesystemNeedsExtraction) }
    }

    @Test
    fun `handleGenerateDownloads finds that filesystemNeedsExtraction is false if it is created from backup`() {
        sessionFsm.setState(AssetListsRetrievalSucceeded(assetList))
        sessionFsm.getState().observeForever(mockStateObserver)

        val filesystemNeedsExtraction = false
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(false)
        filesystem.isCreatedFromBackup = true

        runBlocking {
            whenever(mockAssetRepository.generateDownloadRequirements(filesystem, assetList, filesystemNeedsExtraction))
                    .thenReturn(listOf())

            sessionFsm.submitEvent(GenerateDownloads(filesystem, assetList), this)
        }

        filesystem.isCreatedFromBackup = false // Reset state
        verify(mockStateObserver).onChanged(GeneratingDownloadRequirements)
        verify(mockStateObserver).onChanged(NoDownloadsRequired)
        verifyBlocking(mockAssetRepository) { generateDownloadRequirements(filesystem, assetList, filesystemNeedsExtraction) }
    }

    @Test
    fun `State is DownloadsRequired and and largeDownloadRequired is false if no rootfs file is included in downloads`() {
        sessionFsm.setState(AssetListsRetrievalSucceeded(assetList))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(true)

        runBlocking {
            whenever(mockAssetRepository.generateDownloadRequirements(filesystem, assetList, false))
                    .thenReturn(downloadMetadata)

            sessionFsm.submitEvent(GenerateDownloads(filesystem, assetList), this)
        }

        verify(mockStateObserver).onChanged(GeneratingDownloadRequirements)
        verify(mockStateObserver).onChanged(DownloadsRequired(downloadMetadata, false))
    }

    @Test
    fun `State is DownloadsRequired and and largeDownloadRequired is true if rootfs file is included in downloads`() {
        sessionFsm.setState(AssetListsRetrievalSucceeded(assetList))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(false)

        val metadata = listOf(DownloadMetadata("rootfs.tar.gz", assetType, highVersionCode, url))

        runBlocking {
            whenever(mockAssetRepository.generateDownloadRequirements(filesystem, assetList, true))
                    .thenReturn(metadata)

            sessionFsm.submitEvent(GenerateDownloads(filesystem, assetList), this)
        }

        verify(mockStateObserver).onChanged(GeneratingDownloadRequirements)
        verify(mockStateObserver).onChanged(DownloadsRequired(metadata, true))
    }

    @Test
    fun `State is DownloadsHaveSucceeded once downloads succeed`() {
        sessionFsm.setState(DownloadingAssets(0, 0))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetDownloader.handleDownloadComplete(1))
                .thenReturn(AllDownloadsCompletedSuccessfully)

        runBlocking {
            sessionFsm.submitEvent(AssetDownloadComplete(1), this)
        }

        verify(mockStateObserver).onChanged(DownloadsHaveSucceeded)
    }

    @Test
    fun `State is updated as downloads complete`() {
        sessionFsm.setState(DownloadingAssets(0, 0))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetDownloader.handleDownloadComplete(0))
                .thenReturn(CompletedDownloadsUpdate(1, 3))
        whenever(mockAssetDownloader.handleDownloadComplete(1))
                .thenReturn(CompletedDownloadsUpdate(2, 3))

        runBlocking {
            sessionFsm.submitEvent(AssetDownloadComplete(0), this)
            sessionFsm.submitEvent(AssetDownloadComplete(1), this)
        }

        verify(mockStateObserver).onChanged(DownloadingAssets(1, 3))
        verify(mockStateObserver).onChanged(DownloadingAssets(2, 3))
    }

    @Test
    fun `State is DownloadsHaveFailed if any downloads fail`() {
        sessionFsm.setState(DownloadingAssets(0, 0))
        sessionFsm.getState().observeForever(mockStateObserver)

        val localizationData = DownloadFailureLocalizationData(0)
        whenever(mockAssetDownloader.handleDownloadComplete(0))
                .thenReturn(AssetDownloadFailure(localizationData))

        runBlocking {
            sessionFsm.submitEvent(AssetDownloadComplete(0), this)
        }

        verify(mockStateObserver).onChanged(DownloadsHaveFailed(localizationData))
    }

    @Test
    fun `State is unaffected if we intercept a download enqueued by something else`() {
        sessionFsm.setState(DownloadingAssets(0, 2))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetDownloader.handleDownloadComplete(0))
                .thenReturn(NonUserlandDownloadFound)

        runBlocking {
            sessionFsm.submitEvent(AssetDownloadComplete(0), this)
        }

        verify(mockStateObserver, never()).onChanged(DownloadingAssets(1, 2))
    }

    @Test
    // This case shouldn't ever actually happen
    fun `Passes on illegal cache access attempts`() {
        sessionFsm.setState(DownloadingAssets(0, 0))
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetDownloader.handleDownloadComplete(0))
                .thenReturn(CacheSyncAttemptedWhileCacheIsEmpty)

        runBlocking {
            sessionFsm.submitEvent(AssetDownloadComplete(0), this)
        }

        verify(mockStateObserver).onChanged(AttemptedCacheAccessWhileEmpty)
    }

    @Test
    fun `Does nothing if download cache is empty when receiving SyncDownloadState`() {
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)
        whenever(mockAssetDownloader.downloadStateHasBeenCached())
                .thenReturn(false)

        runBlocking {
            sessionFsm.submitEvent(SyncDownloadState, this)
        }

        verify(mockStateObserver, times(1)).onChanged(WaitingForSessionSelection)
        verifyNoMoreInteractions(mockStateObserver)
    }

    @Test
    fun `Appropriately resets download state if syncing during WaitingForSessionSelection`() {
        sessionFsm.setState(WaitingForSessionSelection)
        sessionFsm.getState().observeForever(mockStateObserver)
        whenever(mockAssetDownloader.downloadStateHasBeenCached())
                .thenReturn(true)
        whenever(mockAssetDownloader.syncStateWithCache())
                .thenReturn(AllDownloadsCompletedSuccessfully)

        runBlocking {
            sessionFsm.submitEvent(SyncDownloadState, this)
        }

        verify(mockStateObserver).onChanged(DownloadingAssets(0, 0))
        verify(mockStateObserver).onChanged(DownloadsHaveSucceeded)
    }

    @Test
    fun `Appropriately resets download state if syncing during DownloadingAssets`() {
        sessionFsm.setState(DownloadingAssets(0, 10))
        sessionFsm.getState().observeForever(mockStateObserver)
        whenever(mockAssetDownloader.downloadStateHasBeenCached())
                .thenReturn(true)
        whenever(mockAssetDownloader.syncStateWithCache())
                .thenReturn(AllDownloadsCompletedSuccessfully)

        runBlocking {
            sessionFsm.submitEvent(SyncDownloadState, this)
        }

        verify(mockStateObserver).onChanged(DownloadingAssets(0, 0))
        verify(mockStateObserver).onChanged(DownloadsHaveSucceeded)
    }

    @Test
    fun `State is LocalDirectoryCopySucceeded if files are moved to correct subdirectories`() {
        sessionFsm.setState(DownloadsHaveSucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking { sessionFsm.submitEvent(CopyDownloadsToLocalStorage, this) }

        verifyBlocking(mockAssetDownloader) { prepareDownloadsForUse(anyOrNull()) }
        verify(mockStateObserver).onChanged(CopyingFilesToLocalDirectories)
        verify(mockStateObserver).onChanged(LocalDirectoryCopySucceeded)
    }

    @Test
    fun `State is LocalDirectoryCopyFailed if a problem arises`() {
        sessionFsm.setState(DownloadsHaveSucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking {
            whenever(mockAssetDownloader.prepareDownloadsForUse(anyOrNull()))
                    .thenThrow(IOException())
            sessionFsm.submitEvent(CopyDownloadsToLocalStorage, this)
        }

        verify(mockStateObserver).onChanged(CopyingFilesToLocalDirectories)
        verify(mockStateObserver).onChanged(LocalDirectoryCopyFailed)
    }

    @Test
    fun `State is FilesystemAssetVerificationSucceeded if all assets are present and filesystem does not need updating`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        filesystem.versionCodeUsed = highVersionCode
        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(true)
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn(lowVersionCode)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(FilesystemAssetVerificationSucceeded)
    }

    @Test
    fun `State is AssetsAreMissingFromSupportDirectories if the assets are not present on a filesystem and are missing from local directories`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn("")
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(false)
        whenever(mockAssetRepository.assetsArePresentInSupportDirectories(assetList))
                .thenReturn(false)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(AssetsAreMissingFromSupportDirectories)
    }

    @Test
    fun `State is AssetsAreMissingFromSupportDirectories if filesystem needs updating and assets are missing from local directories`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        filesystem.versionCodeUsed = lowVersionCode
        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(true)
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn(highVersionCode)
        whenever(mockAssetRepository.assetsArePresentInSupportDirectories(assetList))
                .thenReturn(false)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(AssetsAreMissingFromSupportDirectories)
    }

    @Test
    fun `State is FilesystemAssetVerificationSucceeded if it needs to copy filesystem assets and succeeds`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(false)
        whenever(mockAssetRepository.assetsArePresentInSupportDirectories(assetList))
                .thenReturn(true)
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(false)

        filesystem.versionCodeUsed = lowVersionCode
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn(highVersionCode)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        val updatedFilesystem = filesystem
        updatedFilesystem.versionCodeUsed = highVersionCode
        verify(mockFilesystemManager).copyAssetsToFilesystem(filesystem)
        verify(mockFilesystemDao).updateFilesystem(updatedFilesystem)
        verify(mockFilesystemManager, never()).removeRootfsFilesFromFilesystem("${filesystem.id}")
        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(FilesystemAssetVerificationSucceeded)
    }

    @Test
    fun `Removes rootfs files if the filesystem has already been extracted when updating assets`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(false)
        whenever(mockAssetRepository.assetsArePresentInSupportDirectories(assetList))
                .thenReturn(true)
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(true)

        filesystem.versionCodeUsed = lowVersionCode
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn(highVersionCode)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        val updatedFilesystem = filesystem
        updatedFilesystem.versionCodeUsed = highVersionCode
        verify(mockFilesystemManager).copyAssetsToFilesystem(filesystem)
        verify(mockFilesystemDao).updateFilesystem(updatedFilesystem)
        verify(mockFilesystemManager).removeRootfsFilesFromFilesystem("${filesystem.id}")
        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(FilesystemAssetVerificationSucceeded)
    }

    @Test
    fun `State is FilesystemAssetCopyFailed if filesystem assets need to be copied and that operation fails`() {
        sessionFsm.setState(LocalDirectoryCopySucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockAssetRepository.getDistributionAssetsForExistingFilesystem(filesystem))
                .thenReturn(assetList)
        whenever(mockFilesystemManager.areAllRequiredAssetsPresent("${filesystem.id}", assetList))
                .thenReturn(false)

        filesystem.versionCodeUsed = highVersionCode
        whenever(mockAssetRepository.getLatestDistributionVersion(filesystem.distributionType))
                .thenReturn(lowVersionCode)

        whenever(mockAssetRepository.assetsArePresentInSupportDirectories(assetList))
                .thenReturn(true)
        whenever(mockFilesystemManager.copyAssetsToFilesystem(filesystem))
                .thenThrow(Exception::class.java)

        runBlocking { sessionFsm.submitEvent(VerifyFilesystemAssets(filesystem), this) }

        verify(mockStateObserver).onChanged(VerifyingFilesystemAssets)
        verify(mockStateObserver).onChanged(FilesystemAssetCopyFailed)
    }

    @Test
    fun `State is LowAvailableStorage if device has between 250Mb and 1GB of storage`() {
        sessionFsm.setState(FilesystemAssetVerificationSucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockStorageCalculator.getAvailableStorageInMB()).thenReturn(300)
        runBlocking { sessionFsm.submitEvent(VerifyAvailableStorage, this) }

        verify(mockStateObserver).onChanged(VerifyingSufficientStorage)
        verify(mockStateObserver).onChanged(LowAvailableStorage)
    }

    @Test
    fun `State is VerifyingSufficientStorageFailed if device has between 0MB and 250MB of storage`() {
        sessionFsm.setState(FilesystemAssetVerificationSucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockStorageCalculator.getAvailableStorageInMB()).thenReturn(150)
        runBlocking { sessionFsm.submitEvent(VerifyAvailableStorage, this) }

        verify(mockStateObserver).onChanged(VerifyingSufficientStorage)
        verify(mockStateObserver).onChanged(VerifyingSufficientStorageFailed)
    }

    @Test
    fun `State is not VerifyingSufficientStorageFailed or LowAvailableStorage if device has greater than 1GB of storage after VerifyingAvailableStorage`() {
        sessionFsm.setState(FilesystemAssetVerificationSucceeded)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockStorageCalculator.getAvailableStorageInMB()).thenReturn(1001)
        runBlocking { sessionFsm.submitEvent(VerifyAvailableStorage, this) }

        verify(mockStateObserver).onChanged(VerifyingSufficientStorage)
        verify(mockStateObserver, never()).onChanged(VerifyingSufficientStorageFailed)
        verify(mockStateObserver, never()).onChanged(LowAvailableStorage)
    }

    @Test
    fun `Exits early if filesystem is already extracted`() {
        sessionFsm.setState(StorageVerificationCompletedSuccessfully)
        sessionFsm.getState().observeForever(mockStateObserver)

        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(true)

        runBlocking { sessionFsm.submitEvent(ExtractFilesystem(filesystem), this) }

        verify(mockFilesystemManager, times(1)).hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}")
        verify(mockStateObserver).onChanged(ExtractionHasCompletedSuccessfully)
        verify(mockFilesystemManager).removeRootfsFilesFromFilesystem("${filesystem.id}")
    }

    @Test
    fun `State is ExtractionSucceeded if extraction succeeds`() {
        sessionFsm.setState(StorageVerificationCompletedSuccessfully)
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking {
            whenever(mockFilesystemManager.extractFilesystem(eq(filesystem), anyOrNull()))
                    .thenReturn(SuccessfulExecution)
        }
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(false)
                .thenReturn(true)

        runBlocking { sessionFsm.submitEvent(ExtractFilesystem(filesystem), this) }

        // TODO is there some way to verify extraction steps?
        verify(mockStateObserver).onChanged(ExtractionHasCompletedSuccessfully)
        verify(mockFilesystemManager).removeRootfsFilesFromFilesystem("${filesystem.id}")
    }

    @Test
    fun `State is ExtractionFailed and propagates reason for failure`() {
        sessionFsm.setState(StorageVerificationCompletedSuccessfully)
        sessionFsm.getState().observeForever(mockStateObserver)

        val reason = "reason"
        runBlocking {
            whenever(mockFilesystemManager.extractFilesystem(eq(filesystem), anyOrNull()))
                    .thenReturn(FailedExecution(reason))
        }

        runBlocking { sessionFsm.submitEvent(ExtractFilesystem(filesystem), this) }

        verify(mockStateObserver).onChanged(ExtractionFailed(reason))
    }

    @Test
    fun `State is ExtractionFailed if extraction reportedly succeeds but status file is not created`() {
        sessionFsm.setState(StorageVerificationCompletedSuccessfully)
        sessionFsm.getState().observeForever(mockStateObserver)

        runBlocking {
            whenever(mockFilesystemManager.extractFilesystem(eq(filesystem), anyOrNull()))
                    .thenReturn(SuccessfulExecution)
        }
        whenever(mockFilesystemManager.hasFilesystemBeenSuccessfullyExtracted("${filesystem.id}"))
                .thenReturn(false)
                .thenReturn(false)

        runBlocking { sessionFsm.submitEvent(ExtractFilesystem(filesystem), this) }

        verify(mockStateObserver).onChanged(ExtractionFailed("Unknown reason."))
    }
}
