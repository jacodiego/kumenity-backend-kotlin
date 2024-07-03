package dev.kimun.kumenity.adapters.account.out.persistence

import dev.kimun.kumenity.core.account.application.ports.out.AccountRepository
import dev.kimun.kumenity.core.account.domain.Account
import org.springframework.stereotype.Component
import java.util.*


@Component
class AccountMongoAdapter(private val accountMongoRepository: AccountMongoRepository): AccountRepository {
    override fun findById(id: UUID): Account {
        return accountMongoRepository.findById(id).map(AccountEntity::toDomain).orElseThrow()
    }

    override fun findByEmail(email: String): Account {
        return accountMongoRepository.findByEmail(email).map(AccountEntity::toDomain).orElseThrow()
    }

    override fun save(account: Account): Account {
        return accountMongoRepository.save(AccountEntity.fromDomain(account)).toDomain()
    }

    override fun deleteById(id: UUID): Void {
        TODO("Not yet implemented")
    }

    override fun emailExists(email: String): Boolean {
        return accountMongoRepository.existsByEmail(email)
    }
}