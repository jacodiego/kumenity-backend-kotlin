package dev.kimun.kumenity.adapters.community.out.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface CommunityMongoRepository: MongoRepository<CommunityEntity, UUID> {
}