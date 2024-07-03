package dev.kimun.kumenity.adapters.community.out.persistence

import dev.kimun.kumenity.adapters.account.out.persistence.AccountEntity
import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.community.domain.Community
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("communities")
data class CommunityEntity(@Id private val id: UUID, private val name: String, @DBRef private val owner: AccountEntity, private val private: Boolean) {

    fun toDomain(): Community {
        return Community(
            id,
            name,
            owner.toDomain(),
            private
        )
    }

    companion object {
        fun fromDomain(community: Community): CommunityEntity {
            val entity = CommunityEntity(community.id, community.name, AccountEntity.fromDomain(community.owner), community.private)
            return entity
        }
    }
}