package com.manishsputnikcorporation.safebites.domain.model.base

interface Identifiable {
  val id: String
}

data class CustomIdentifiable(override val id: String) : Identifiable
