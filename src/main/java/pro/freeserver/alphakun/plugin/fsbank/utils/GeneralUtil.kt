package pro.freeserver.alphakun.plugin.fsbank.utils

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import java.math.BigDecimal
import kotlin.math.floor

class GeneralUtil {

    fun currencyFormat(value: Long): String {
        val placeholder = FSBank.mainConfig.currencyFormat
        val plural = FSBank.mainConfig.currencyPlural
        val intPart = value / MULTIPLIER
        val fracPart = value % MULTIPLIER
        return placeholder
            .replace("{major}", BigDecimal.valueOf(intPart).toPlainString(), true)
            .replace("{minor}", BigDecimal.valueOf(fracPart).toPlainString(), true)
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
        fun sendMessage(player: Player, message: String, messageType: MessagePrefix = MessagePrefix.INFO) {
            player.sendMessage(Component.text(messageType.text + message))
        }
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