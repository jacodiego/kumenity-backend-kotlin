package dev.kimun.kumenity.core.account.application.ports.`in`

import java.util.UUID

interface InvitationUseCase {
    fun sendInvitation(communityId: UUID, email: String) : Unit
    fun acceptInvitation(communityId: UUID, accountId: UUID, nickname: String) : Unit
    fun rejectInvitation(communityId: UUID, accountId: UUID) : Unit
}