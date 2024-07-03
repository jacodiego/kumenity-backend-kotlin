package dev.kimun.kumenity.core.community.domain

import dev.kimun.kumenity.core.account.domain.Account
import java.util.*

data class Community(val id: UUID, val name: String, val owner: Account, val private: Boolean)
