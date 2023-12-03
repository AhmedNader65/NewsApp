package com.me.newsApp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.IOException

abstract class AppErrors : IOException() {
}
data class NetworkUnavailableError(
    override val message: String = "Network Unavailable"
): AppErrors()

data class ServerError(
    override val message: String = "Server Error"
): AppErrors()

data class UnknownError(
    override val message: String = "Unknown Error"
): AppErrors()

