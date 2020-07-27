package org.mozilla.rocket.content.news.domain

import org.mozilla.rocket.content.Result
import org.mozilla.rocket.content.isNotEmpty
import org.mozilla.rocket.content.news.data.NewsLanguage
import org.mozilla.rocket.content.news.data.NewsSettingsRepository
import java.util.Locale

class LoadNewsLanguagesUseCase(private val repository: NewsSettingsRepository) {

    suspend operator fun invoke(): Result<List<NewsLanguage>> { //#overloaded_op
        var defaultLanguage = repository.getDefaultLanguage() //#inference
        val result = repository.getLanguages() //#inference
        if (result is Result.Success && result.isNotEmpty) {
            val supportLanguages = result.data //#inference
            if (supportLanguages.find { newsLanguage -> newsLanguage.isSelected } == null) { //#lambda
                supportLanguages
                    .find { language -> Locale.getDefault().displayName.contains(language.name) } //#lambda
                    ?.let { defaultLanguage = it } //#safe_call,lambda

                supportLanguages.forEach { //#lambda
                    it.isSelected = (it.key == defaultLanguage.key)
                }
            }
        }
        return if (result.isNotEmpty) {
            result
        } else {
            Result.Success(listOf(repository.getDefaultLanguage()))
        }
    }
}
