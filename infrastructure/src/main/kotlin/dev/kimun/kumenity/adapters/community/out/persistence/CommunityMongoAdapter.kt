package dev.kimun.kumenity.adapters.community.out.persistence

import dev.kimun.kumenity.adapters.account.out.persistence.AccountEntity
import dev.kimun.kumenity.core.community.application.ports.out.CommunityRepository
import dev.kimun.kumenity.core.community.domain.Community
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommunityMongoAdapter(private val communityMongoRepository: CommunityMongoRepository): CommunityRepository {
    override fun findById(id: UUID): Community {
        return communityMongoRepository.findById(id).map(CommunityEntity::toDomain).orElseThrow()
    }

    override fun save(community: Community): Community {
        return communityMongoRepository.save(CommunityEntity.fromDomain(community)).toDomain()
    }

    override fun deleteById(id: UUID) {
        communityMongoRepository.deleteById(id)
    }
}