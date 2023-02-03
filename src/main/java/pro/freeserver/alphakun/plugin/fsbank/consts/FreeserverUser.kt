package pro.freeserver.alphakun.plugin.fsbank.consts

import java.util.*

data class FreeserverUser constructor(
    val mcuuid: UUID,
    val discord_id: String?,
    val permissions: String,
    val bank_id: UUID?,
    val wallet_id: UUID?
)