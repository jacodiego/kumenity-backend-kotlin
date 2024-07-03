package dev.kimun.kumenity.adapters.community.`in`.rest

import dev.kimun.kumenity.adapters.community.`in`.rest.requests.CreateCommunityRequest
import dev.kimun.kumenity.adapters.community.`in`.rest.responses.CreateCommunityResponse
import dev.kimun.kumenity.core.community.application.ports.`in`.CreateCommunityUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class CommunityController(private final val createCommunityUseCase: CreateCommunityUseCase) {

    @PostMapping("/community")
    fun createCommunity(@RequestBody request: CreateCommunityRequest): ResponseEntity<CreateCommunityResponse> {
        val community = createCommunityUseCase.createCommunity(request.owner, request.name, request.private)
        return ResponseEntity.ok(CreateCommunityResponse.fromDomain(community))
    }
}