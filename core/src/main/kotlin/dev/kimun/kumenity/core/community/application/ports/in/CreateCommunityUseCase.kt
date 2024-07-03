package dev.kimun.kumenity.core.community.application.ports.`in`

import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.community.domain.Community
import java.util.UUID

interface CreateCommunityUseCase {
    fun createCommunity(owner: UUID, name: String, private: Boolean) : Community
}