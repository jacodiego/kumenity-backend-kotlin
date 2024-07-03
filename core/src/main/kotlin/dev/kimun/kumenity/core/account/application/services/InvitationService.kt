package dev.kimun.kumenity.core.account.application.services

import dev.kimun.kumenity.arch.UseCase
import dev.kimun.kumenity.core.account.application.ports.out.AccountRepository
import dev.kimun.kumenity.core.account.application.exceptions.AccountIsAlreadyInvited
import dev.kimun.kumenity.core.account.application.exceptions.AccountIsAlreadyMember
import dev.kimun.kumenity.core.account.application.exceptions.InvitationNotExists
import dev.kimun.kumenity.core.account.application.ports.`in`.InvitationUseCase
import dev.kimun.kumenity.core.community.application.ports.out.CommunityRepository
import java.util.*

@UseCase
class InvitationService(private val communityRepository: CommunityRepository, private val accountRepository: AccountRepository) :
    InvitationUseCase {
    override fun sendInvitation(communityId: UUID, email: String): Unit {
        val account = accountRepository.findByEmail(email)
        val community = communityRepository.findById(communityId)
        if( account.memberships.any { it.community.id == community.id } )
            throw AccountIsAlreadyMember("The email $email is already a member of this community")
        if( account.invitations.any { it.community.id == community.id } )
            throw AccountIsAlreadyInvited("The email $email is already invited to this community")
        account.sendInvitation(community)
        accountRepository.save(account)
    }

    override fun acceptInvitation(communityId: UUID, accountId: UUID, nickname: String): Unit {
        val account = accountRepository.findById(accountId)
        val community = communityRepository.findById(communityId)
        if( account.memberships.any { it.community.id == community.id } )
            throw AccountIsAlreadyMember("You are already a member of this community")
        if( !account.invitations.any { it.community.id == community.id } )
            throw InvitationNotExists("You don't have an invitation to this community")
        account.joinToCommunity(community, nickname)
        account.acceptInvitation(community)
        accountRepository.save(account)
    }

    override fun rejectInvitation(communityId: UUID, accountId: UUID): Unit {
        val account = accountRepository.findById(accountId)
        val community = communityRepository.findById(communityId)
        if( !account.invitations.any { it.community.id == community.id } )
            throw InvitationNotExists("You don't have an invitation to this community")
        account.rejectInvitation(community)
        accountRepository.save(account)
    }
}