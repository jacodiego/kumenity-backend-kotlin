package dev.kimun.kumenity.adapters.community.`in`.rest.responses

import dev.kimun.kumenity.core.community.domain.Community
import java.util.UUID

@JvmRecord
data class CreateCommunityResponse(
    val id: UUID,
    val name: String,
) {
    companion object {
        fun fromDomain(community: Community): CreateCommunityResponse {
            return CreateCommunityResponse(
                community.id,
                community.name
            )
        }
    }
}