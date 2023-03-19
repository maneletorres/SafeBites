package com.manishsputnikcorporation.safebites.domain.usecase.base.flow

import androidx.annotation.CheckResult

@CheckResult fun <R> FlowUseCase<Unit, R>.prepare() = prepare(Unit)

@CheckResult
inline fun <reified R> GenericFlowUseCase<Unit>.prepare() = prepare(Unit, R::class.java)
