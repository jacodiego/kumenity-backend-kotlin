package dev.kimun.kumenity.adapters.account.`in`.rest

import dev.kimun.kumenity.adapters.account.`in`.rest.requests.*
import dev.kimun.kumenity.adapters.account.`in`.rest.responses.RegisterAccountResponse
import dev.kimun.kumenity.core.account.application.exceptions.*
import dev.kimun.kumenity.core.account.application.ports.`in`.InvitationUseCase
import dev.kimun.kumenity.core.account.application.ports.`in`.JoinToCommunityUseCase
import dev.kimun.kumenity.core.account.application.ports.`in`.RegisterAccountUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException




@RestController
class AccountController(
    private final val registerAccountUseCase: RegisterAccountUseCase,
    private final val joinToCommunityUseCase: JoinToCommunityUseCase,
    private final val invitationUseCase: InvitationUseCase
) {

    @PostMapping("/account")
    fun registerAccount(@RequestBody request: RegisterAccountRequest): ResponseEntity<RegisterAccountResponse> {
        try {
            val account = registerAccountUseCase.registerAccount(request.email, request.password)
            return ResponseEntity.ok(RegisterAccountResponse.fromDomain(account))
        } catch (e: AccountAlreadyExists) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "E-mail already exists", e
            )
        }
    }

    @PostMapping("/account/join")
    fun joinToCommunity(@RequestBody request: JoinToCommunityRequest): ResponseEntity<Boolean> {
        try {
            joinToCommunityUseCase.joinToPublicCommunity(request.accountId, request.communityId, request.nickname)
            return ResponseEntity.ok(true)
        } catch (e: CommunityIsPrivate) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "This community is not public", e
            )
        } catch (e: AccountIsAlreadyMember) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "This account is already member", e
            )
        }
    }

    @PostMapping("/account/invite")
    fun inviteToCommunity(@RequestBody request: InvitationToCommunityRequest): ResponseEntity<Boolean> {
        try {
            invitationUseCase.sendInvitation(request.communityId, request.email)
            return ResponseEntity.ok(true)
        } catch (e: AccountIsAlreadyMember) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "This account is already a member", e
            )
        } catch (e: AccountIsAlreadyInvited) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "This account has already been invited", e
            )
        }
    }

    @PostMapping("/account/accept_invitation")
    fun acceptInvitation(@RequestBody request: AcceptInvitationRequest): ResponseEntity<Boolean> {
        try {
            invitationUseCase.acceptInvitation(request.communityId, request.accountId, request.nickname)
            return ResponseEntity.ok(true)
        } catch (e: InvitationNotExists) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "You don't have an invitation for this community", e
            )
        } catch (e: AccountIsAlreadyMember) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "You are already a member of this community", e
            )
        }
    }

    @PostMapping("/account/reject_invitation")
    fun rejectInvitation(@RequestBody request: RejectInvitationRequest): ResponseEntity<Boolean> {
        try {
            invitationUseCase.rejectInvitation(request.communityId, request.accountId)
            return ResponseEntity.ok(true)
        } catch (e: InvitationNotExists) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT, "You don't have an invitation for this community", e
            )
        }
    }
}