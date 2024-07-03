package dev.kimun.kumenity.adapters.account.`in`.rest.requests

import java.util.UUID

@JvmRecord
data class RejectInvitationRequest(
    val communityId: UUID,
    val accountId: UUID,
)