package org.mozilla.rocket.content.travel.domain

import org.mozilla.rocket.content.Result
import org.mozilla.rocket.content.travel.data.BcHotelApiEntity
import org.mozilla.rocket.content.travel.data.TravelRepository

class GetCityHotelsUseCase(private val travelRepository: TravelRepository) {

    suspend operator fun invoke(cityId: String, offset: Int): Result<BcHotelApiEntity> =
            travelRepository.getCityHotels(cityId, offset)
}