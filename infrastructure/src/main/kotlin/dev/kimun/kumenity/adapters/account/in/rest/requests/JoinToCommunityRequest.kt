package dev.kimun.kumenity.adapters.account.`in`.rest.requests

import java.util.UUID

@JvmRecord
data class JoinToCommunityRequest(
    val accountId: UUID,
    val communityId: UUID,
    val nickname: String
)