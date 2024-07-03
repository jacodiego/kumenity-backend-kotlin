package dev.kimun.kumenity.core.account.application.services

import dev.kimun.kumenity.arch.UseCase
import dev.kimun.kumenity.core.account.application.exceptions.AccountAlreadyExists
import dev.kimun.kumenity.core.account.domain.Account
import dev.kimun.kumenity.core.account.application.ports.out.AccountRepository
import dev.kimun.kumenity.core.account.application.ports.`in`.RegisterAccountUseCase
import java.util.*

@UseCase
class RegisterAccountService(private val accountRepository: AccountRepository) : RegisterAccountUseCase {

    override fun registerAccount(email: String, password: String): Account {
        if(accountRepository.emailExists(email)) {
            throw AccountAlreadyExists("The email: $email is already in use")
        }
        return accountRepository.save(Account(UUID.randomUUID(), email, password))
    }
}