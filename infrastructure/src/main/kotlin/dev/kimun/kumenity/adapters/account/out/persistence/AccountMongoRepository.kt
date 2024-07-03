package dev.kimun.kumenity.adapters.account.out.persistence

import dev.kimun.kumenity.core.account.domain.Account
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*
import javax.swing.text.html.Option

interface AccountMongoRepository: MongoRepository<AccountEntity, UUID> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<AccountEntity>
}