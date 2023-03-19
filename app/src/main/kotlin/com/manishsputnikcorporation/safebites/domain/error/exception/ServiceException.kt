package com.manishsputnikcorporation.safebites.domain.error.exception

class ServiceException(message: String, url: String?) : CustomAppException(message, url)
