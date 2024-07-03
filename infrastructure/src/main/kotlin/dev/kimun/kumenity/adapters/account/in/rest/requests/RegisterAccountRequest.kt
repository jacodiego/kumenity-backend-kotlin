package dev.kimun.kumenity.adapters.account.`in`.rest.requests

@JvmRecord
data class RegisterAccountRequest(
    val email: String,
    val password: String
)