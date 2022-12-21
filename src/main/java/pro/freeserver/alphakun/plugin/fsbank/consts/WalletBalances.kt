package pro.freeserver.alphakun.plugin.fsbank.consts

import java.util.UUID

data class WalletBalances(
    val id: UUID,
    val balance: Number,
    val mcuuid: UUID
)