package dev.kimun.kumenity.adapters.account.out.persistence

import dev.kimun.kumenity.adapters.community.out.persistence.CommunityEntity
import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.account.domain.Invitation
import dev.kimun.kumenity.core.account.domain.Membership
import dev.kimun.kumenity.core.account.domain.StatusInvitation
import dev.kimun.kumenity.core.community.domain.Community
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.lang.reflect.Member
import java.util.UUID

@Document("accounts")
data class AccountEntity(@Id private val id: UUID, private val email: String, private val password: String, private val memberships: List<MembershipEntity>, private val invitations: List<InvitationEntity>) {
    fun toDomain(): Account {
        return Account(
            id,
            email,
            password,
            memberships.map { it.toDomain() },
            invitations.map { it.toDomain() }
        )
    }

    companion object {
        fun fromDomain(account: Account): AccountEntity {
            val entity = AccountEntity(account.id, account.email, account.password, account.memberships.map { MembershipEntity.fromDomain(it) }, account.invitations.map { InvitationEntity.fromDomain(it) })
            return entity
        }
    }
}

data class MembershipEntity(@DBRef private val community: CommunityEntity, private val nickname: String) {
    fun toDomain(): Membership {
        return Membership(
            community.toDomain(),
            nickname
        )
    }

    companion object {
        fun fromDomain(membership: Membership): MembershipEntity {
            val entity = MembershipEntity(CommunityEntity.fromDomain(membership.community), membership.nickname)
            return entity
        }
    }
}

data class InvitationEntity(@DBRef private val community: CommunityEntity, private val status: StatusInvitation) {
    fun toDomain(): Invitation {
        return Invitation(
            community.toDomain(),
            status
        )
    }

    companion object {
        fun fromDomain(invitation: Invitation): InvitationEntity {
            val entity = InvitationEntity(CommunityEntity.fromDomain(invitation.community), invitation.status)
            return entity
        }
    }
}