package com.manishsputnikcorporation.safebites.domain.error.exception

abstract class CustomAppException(message: String, val url: String? = null) : Exception(message)
