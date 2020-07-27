package fi.kroon.vadret.data.weatherforecast

import dagger.Lazy
import fi.kroon.vadret.data.exception.ErrorHandler
import fi.kroon.vadret.data.exception.ExceptionHandler
import fi.kroon.vadret.data.exception.IErrorHandler
import fi.kroon.vadret.data.exception.IExceptionHandler
import fi.kroon.vadret.data.failure.Failure
import fi.kroon.vadret.data.weatherforecast.exception.WeatherForecastFailure
import fi.kroon.vadret.data.weatherforecast.model.Weather
import fi.kroon.vadret.data.weatherforecast.model.WeatherOut
import fi.kroon.vadret.data.weatherforecast.net.WeatherForecastNetDataSource
import fi.kroon.vadret.di.scope.CoreApplicationScope
import fi.kroon.vadret.util.HTTP_200_OK
import fi.kroon.vadret.util.HTTP_204_NO_CONTENT
import fi.kroon.vadret.util.HTTP_400_BAD_REQUEST
import fi.kroon.vadret.util.HTTP_403_FORBIDDEN
import fi.kroon.vadret.util.HTTP_404_NOT_FOUND
import fi.kroon.vadret.util.HTTP_500_INTERNAL_SERVER_ERROR
import fi.kroon.vadret.util.HTTP_503_SERVICE_UNAVAILABLE
import fi.kroon.vadret.util.HTTP_504_GATEWAY_TIMEOUT
import fi.kroon.vadret.util.NetworkHandler
import fi.kroon.vadret.util.extension.asLeft
import fi.kroon.vadret.util.extension.toCoordinate
import io.github.sphrak.either.Either
import io.reactivex.Single
import javax.inject.Inject
import retrofit2.Response

@CoreApplicationScope
class WeatherForecastRepository @Inject constructor(
    private val weatherForecastNetDataSource: Lazy<WeatherForecastNetDataSource>,
    private val networkHandler: NetworkHandler,
    private val errorHandler: ErrorHandler,
    private val exceptionHandler: ExceptionHandler
) : IErrorHandler by errorHandler, IExceptionHandler<Failure> by exceptionHandler {

    operator fun invoke(request: WeatherOut): Single<Either<Failure, Weather>> =
        when (networkHandler.isConnected) {
            true -> weatherForecastNetDataSource
                .get()
                .getWeatherForecast(
                    request.category,
                    request.version,
                    request.longitude.toCoordinate(),
                    request.latitude.toCoordinate()
                ).map { response: Response<Weather> ->
                    when (response.code()) {
                        HTTP_200_OK -> Either.Right(response.body()!!)
                        HTTP_204_NO_CONTENT -> WeatherForecastFailure.NoWeatherAvailable.asLeft()
                        HTTP_403_FORBIDDEN -> Failure.HttpForbidden403.asLeft()
                        HTTP_404_NOT_FOUND -> WeatherForecastFailure.NoWeatherAvailableForThisLocation.asLeft()
                        HTTP_400_BAD_REQUEST -> Failure.HttpBadRequest400.asLeft()
                        HTTP_500_INTERNAL_SERVER_ERROR -> Failure.HttpInternalServerError500.asLeft()
                        HTTP_503_SERVICE_UNAVAILABLE -> Failure.HttpServiceUnavailable503.asLeft()
                        HTTP_504_GATEWAY_TIMEOUT -> Failure.HttpGatewayTimeout504.asLeft()
                        else -> WeatherForecastFailure.NoWeatherAvailable.asLeft()
                    }
                }
            false -> getNetworkOfflineError()
        }.onErrorReturn {
            exceptionHandler(it)
                .asLeft()
        }
}