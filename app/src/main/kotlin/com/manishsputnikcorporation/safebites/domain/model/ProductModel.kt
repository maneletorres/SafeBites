package com.manishsputnikcorporation.safebites.domain.model

import android.os.Parcelable
import com.manishsputnikcorporation.safebites.domain.model.base.Identifiable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductModel(
    override val id: String,
    val name: String,
    val imageUrl: String,
) : Identifiable, Parcelable
