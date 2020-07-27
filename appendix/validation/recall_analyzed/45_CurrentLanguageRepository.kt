package chat.rocket.android.server.infrastructure

interface CurrentLanguageRepository {

    fun save(language: String, country: String? = null) //#func_with_default_value
    fun getLanguage(): String?
    fun getCountry(): String?
}