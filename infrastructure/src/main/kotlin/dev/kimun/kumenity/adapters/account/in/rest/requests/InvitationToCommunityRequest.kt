package dev.kimun.kumenity.adapters.account.`in`.rest.requests

import java.util.UUID

@JvmRecord
data class InvitationToCommunityRequest(
    val communityId: UUID,
    val email: String
)