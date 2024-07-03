package dev.kimun.kumenity.core.account.application.ports.`in`

import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.community.domain.Community
import java.util.UUID

interface JoinToCommunityUseCase {
    fun joinToPublicCommunity(accountId: UUID, communityId: UUID, nickname: String) : Unit
}