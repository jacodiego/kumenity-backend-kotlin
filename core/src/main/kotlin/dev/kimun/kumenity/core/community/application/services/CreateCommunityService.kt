package dev.kimun.kumenity.core.community.application.services

import dev.kimun.kumenity.arch.UseCase
import dev.kimun.kumenity.core.account.application.ports.out.AccountRepository
import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.community.application.ports.`in`.CreateCommunityUseCase
import dev.kimun.kumenity.core.community.application.ports.out.CommunityRepository
import dev.kimun.kumenity.core.community.domain.Community
import java.util.*

@UseCase
class CreateCommunityService(private val communityRepository: CommunityRepository, private val accountRepository: AccountRepository) : CreateCommunityUseCase {
    override fun createCommunity(owner: UUID, name: String, private: Boolean): Community {
        return communityRepository.save(Community(UUID.randomUUID(), name, accountRepository.findById(owner), private))
    }
}