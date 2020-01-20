package fi.kroon.vadret.domain.radar

import fi.kroon.vadret.data.failure.Failure
import fi.kroon.vadret.data.radar.model.File
import fi.kroon.vadret.data.radar.model.Radar
import fi.kroon.vadret.data.radar.model.RadarRequest
import fi.kroon.vadret.util.FIFTEEN_MINUTES_IN_MILLIS
import fi.kroon.vadret.util.OFF_BY_ONE
import fi.kroon.vadret.util.common.IDateTimeUtil
import fi.kroon.vadret.util.extension.asSingle
import fi.kroon.vadret.util.extension.flatMapSingle
import io.github.sphrak.either.Either
import io.github.sphrak.either.flatMap
import io.github.sphrak.either.map
import io.reactivex.Single
import io.reactivex.rxkotlin.zipWith
import javax.inject.Inject
import timber.log.Timber

class GetRadarImageUrlService @Inject constructor(
    private val getRadarImageUrlTask: GetRadarImageUrlTask,
    private val getRadarDiskCacheTask: GetRadarDiskCacheTask,
    private val getRadarMemoryCacheTask: GetRadarMemoryCacheTask,
    private val setRadarDiskCacheTask: SetRadarDiskCacheTask,
    private val setRadarMemoryCacheTask: SetRadarMemoryCacheTask,
    private val iDateTimeUtil: IDateTimeUtil
) : IDateTimeUtil by iDateTimeUtil {

    data class Data(
        val index: Int,
        val maxIndex: Int? = null,
        val timeStamp: Long,
        val radar: Radar? = null,
        val radarRequest: RadarRequest = RadarRequest(),
        val file: File? = null
    )

    operator fun invoke(timeStamp: Long, index: Int): Single<Either<Failure, Data>> =
        Data(
            timeStamp = timeStamp,
            index = index
        ).asSingle()
            .flatMap(::getRadar)
            .map(::getFileByIndex)

    private fun getFileByIndex(either: Either<Failure, Data>): Either<Failure, Data> =
        either.map { data: Data ->
            val file: File = data.radar!!.files[data.index]
            val maxIndex: Int = getMaxIndex(radar = data.radar)
            data.copy(file = file, maxIndex = maxIndex)
        }

    private fun getMaxIndex(radar: Radar): Int = radar.files.size - OFF_BY_ONE

    private fun getRadar(data: Data): Single<Either<Failure, Data>> =
        with(data) {
            when {
                (currentTimeMillis() > (timeStamp + FIFTEEN_MINUTES_IN_MILLIS)) -> {
                    getRadarImageUrlTask(radarRequest).map { either ->
                        either.map { radar: Radar ->
                            data.copy(radar = radar, timeStamp = currentTimeMillis())
                        }
                    }.flatMap { data ->
                        updateCache(data)
                    }
                }
                else -> {
                    Single.merge(
                        getRadarDiskCacheTask()
                            .map { either: Either<Failure, Radar> ->
                                either.map { radar: Radar ->
                                    data.copy(radar = radar)
                                }
                            },
                        getRadarMemoryCacheTask()
                            .map { either: Either<Failure, Radar> ->
                                either.map { radar: Radar ->
                                    data.copy(radar = radar)
                                }
                            }
                    ).filter { result: Either<Failure, Data> ->
                        result.either(
                            {
                                false
                            },
                            { data ->
                                Timber.d("Fetched Radar from cache: ${data.radar?.files?.size}")
                                data.radar!!.files.isNotEmpty()
                            }
                        )
                    }.take(1)
                        .switchIfEmpty(
                            getRadarImageUrlTask(data.radarRequest)
                                .map { either: Either<Failure, Radar> ->
                                    Timber.d("Cache was empty, fetching radar from network.")
                                    either.map { radar: Radar ->
                                        data.copy(
                                            radar = radar,
                                            timeStamp = currentTimeMillis()
                                        )
                                    }
                                }.flatMap { data: Either<Failure, Data> ->
                                    updateCache(data)
                                }.toFlowable()
                        ).singleOrError()
                }
            }
        }

    private fun updateCache(either: Either<Failure, Data>): Single<Either<Failure, Data>> =
        either.flatMapSingle { data: Data ->
            setRadarMemoryCacheTask(data.radar!!)
                .zipWith(setRadarDiskCacheTask(data.radar))
                .map { pair: Pair<Either<Failure, Radar>, Either<Failure, Radar>> ->
                    Timber.i("Updating cache")
                    val (firstEither: Either<Failure, Radar>,
                        secondEither: Either<Failure, Radar>) = pair
                    firstEither.flatMap {
                        secondEither.map {
                            data
                        }
                    }
                }
        }
}