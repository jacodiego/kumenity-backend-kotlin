package dev.kimun.kumenity.core.account.application.services

import dev.kimun.kumenity.arch.UseCase
import dev.kimun.kumenity.core.account.application.exceptions.AccountIsAlreadyMember
import dev.kimun.kumenity.core.account.application.exceptions.CommunityIsPrivate
import dev.kimun.kumenity.core.account.application.ports.`in`.JoinToCommunityUseCase
import dev.kimun.kumenity.core.account.application.ports.out.AccountRepository
import dev.kimun.kumenity.core.community.application.ports.out.CommunityRepository
import java.util.*

@UseCase
class JoinToCommunityService(private val accountRepository: AccountRepository, private val communityRepository: CommunityRepository) : JoinToCommunityUseCase {

    override fun joinToPublicCommunity(accountId: UUID, communityId: UUID, nickname: String): Unit {
        val account = accountRepository.findById(accountId)
        val community = communityRepository.findById(communityId)
        if(community.private)
            throw CommunityIsPrivate("This community is private, you need an invitation")
        if( account.memberships.any { it.community.id == community.id } )
            throw AccountIsAlreadyMember("The account is already a member of this community")
        account.joinToCommunity(community, nickname)
        accountRepository.save(account)
    }
}