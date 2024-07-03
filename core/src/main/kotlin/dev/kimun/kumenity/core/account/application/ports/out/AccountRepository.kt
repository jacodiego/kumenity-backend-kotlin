package dev.kimun.kumenity.core.account.application.ports.out

import dev.kimun.kumenity.core.account.domain.Account
import java.util.*

interface AccountRepository {
    fun findById(id: UUID): Account
    fun findByEmail(email: String): Account
    fun save(account: Account): Account
    fun deleteById(id: UUID): Void
    fun emailExists(email: String): Boolean
}