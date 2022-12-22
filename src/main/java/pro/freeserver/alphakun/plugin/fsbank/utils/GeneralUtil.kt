package pro.freeserver.alphakun.plugin.fsbank.utils

import pro.freeserver.alphakun.plugin.fsbank.FSBank
import kotlin.math.floor

class GeneralUtil {

    fun currencyFormat(value: Long): String {
        val placeholder = FSBank.mainConfig.currencyFormat
        val plural = FSBank.mainConfig.currencyPlural
        val intPart = value / MULTIPLIER
        val fracPart = value % MULTIPLIER
        return placeholder
            .replace("{major}", intPart.toString(), true)
            .replace("{minor}", fracPart.toString(), true)
            .replace("{plural}", plural, true)
    }

    fun toLong(value: Double): Long {
        return (value * MULTIPLIER).toLong()
    }

    fun toDouble(value: Long): Double {
        val intPart = value / MULTIPLIER
        val fracPart = value % MULTIPLIER
        return (intPart + fracPart).toDouble()
    }
    companion object {
        const val FRACTIONAL_DIGITS = 2
        const val MULTIPLIER = 100
    }

}

enum class TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER
}

enum class TransactionStatus {
    SUCCESS,
    FAILED,
    PENDING
}

enum class MessagePrefix(val text: String) {
    INFO("§a[§fFSBank§a]§f:§r"),
    WARN("§e[§fFSBank§e]§f:§r"),
    ERROR("§c[§fFSBank§c]§f:§r")
}
enum class TableName(val text: String) {
    FREESERVER_USER("freeserver_user"),
    BANK_BALANCES("bank_balances"),
    WALLET_BALANCES("wallet_balances")
}