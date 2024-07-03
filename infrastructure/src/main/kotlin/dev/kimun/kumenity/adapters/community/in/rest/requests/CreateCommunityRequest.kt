package dev.kimun.kumenity.adapters.community.`in`.rest.requests

import java.util.UUID

@JvmRecord
data class CreateCommunityRequest(
    val name: String,
    val owner: UUID,
    val private: Boolean
)