package net.stew.stew

data class CurrentSession(val userName: String, val userIdCookie: String, val sessionIdCookie: String, val csrfToken: String) { //#data_class
    val userId: Int
        get() = userIdCookie.split(Regex("-"), 2)[0].toInt()
}