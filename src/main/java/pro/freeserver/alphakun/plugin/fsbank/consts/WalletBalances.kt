package pro.freeserver.alphakun.plugin.fsbank.consts

import java.util.UUID

data class WalletBalances(
    val id: UUID = UUID.randomUUID(),
    val balance: Long,
    val mcuuid: UUID
)