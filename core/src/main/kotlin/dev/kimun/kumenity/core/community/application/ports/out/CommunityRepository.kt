package dev.kimun.kumenity.core.community.application.ports.out

import dev.kimun.kumenity.core.community.domain.Community
import java.util.*

interface CommunityRepository {
    fun findById(id: UUID): Community
    fun save(community: Community): Community
    fun deleteById(id: UUID)
}