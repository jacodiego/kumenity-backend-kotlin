package dev.kimun.kumenity.core.account.application.ports.`in`

import dev.kimun.kumenity.core.account.domain.Account

interface RegisterAccountUseCase {
    fun registerAccount(email: String, password: String) : Account
}