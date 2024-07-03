package dev.kimun.kumenity.core.account.domain

import dev.kimun.kumenity.core.community.domain.Community
import java.util.*

class Account(val id: UUID, val email: String, val password: String, val memberships: List<Membership> = ArrayList<Membership>(), val invitations: List<Invitation> = ArrayList<Invitation>()) {
    fun joinToCommunity(community: Community, nickname: String) {
        memberships.addLast(Membership(community, nickname))
    }

    fun sendInvitation(community: Community) {
        invitations.addLast(Invitation(community, StatusInvitation.SENDED))
    }

    fun acceptInvitation(community: Community) {
        invitations.find { it.community.id == community.id }?.status = StatusInvitation.ACCEPTED
    }

    fun rejectInvitation(community: Community) {
        invitations.find { it.community.id == community.id }?.status = StatusInvitation.REJECTED
    }
}

class Membership(val community: Community, val nickname: String)

class Invitation(val community: Community, var status: StatusInvitation)

enum class StatusInvitation {
    SENDED, ACCEPTED, REJECTED
}