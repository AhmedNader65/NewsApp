package com.me.newsApp.network.model

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") :
    IOException(message)

class ServerException(message: String) : Exception(message)
class UnkownException(message: String) : Exception(message)