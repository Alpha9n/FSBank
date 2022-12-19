package pro.freeserver.alphakun.plugin.fsbank.type

import java.util.*

data class FreeserverUser constructor(
    val mcuuid: UUID,
    val discord_id: String,
    val bank_id: UUID?,
    val wallet_id: UUID?
)