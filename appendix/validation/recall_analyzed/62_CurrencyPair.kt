package com.bubelov.coins.model

enum class CurrencyPair(pair: Pair<String, String>) {
    BTC_USD(Pair("BTC", "USD")),
    BTC_EUR(Pair("BTC", "EUR")),
    BTC_GBP(Pair("BTC", "GBP"));

    val baseCurrency = pair.first //#inference
    val quoteCurrency = pair.second //#inference

    override fun toString(): String {
        return "$baseCurrency/$quoteCurrency" //#string_template
    }
}