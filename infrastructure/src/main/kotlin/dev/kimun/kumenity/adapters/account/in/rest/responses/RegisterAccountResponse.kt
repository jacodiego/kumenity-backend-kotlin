package dev.kimun.kumenity.adapters.account.`in`.rest.responses

import dev.kimun.kumenity.core.account.domain.Account
import java.util.UUID

@JvmRecord
data class RegisterAccountResponse(
    val id: UUID,
    val email: String,
) {
    companion object {
        fun fromDomain(account: Account): RegisterAccountResponse {
            return RegisterAccountResponse(
                account.id,
                account.email
            )
        }
    }
}