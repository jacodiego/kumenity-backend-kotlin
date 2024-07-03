package dev.kimun.kumenity.adapters.account.`in`.rest.requests

import java.util.UUID

@JvmRecord
data class AcceptInvitationRequest(
    val communityId: UUID,
    val accountId: UUID,
    val nickname: String
)