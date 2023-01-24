package pro.freeserver.alphakun.plugin.fsbank.command

import net.milkbowl.vault.economy.Economy
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import pro.freeserver.alphakun.plugin.fsbank.utils.MessagePrefix

object Pay {
    fun defaultPay(sender: Player, dist: OfflinePlayer, amount: Double, econ: Economy) {

        if(econ.has(sender,amount)) {
            if(econ.withdrawPlayer(sender,amount).transactionSuccess() && econ.depositPlayer(dist,amount).transactionSuccess()) {
                GeneralUtil.sendMessage(
                    sender,
                    "${amount}${econ.currencyNameSingular()}§aの送金に成功しました"
                )
                dist.player?.let {
                    GeneralUtil.sendMessage(
                        it,
                        "${sender.name}§aから§r${amount}${econ.currencyNameSingular()}§aの入金がありました。"
                    )
                }
                return
            } else {
                GeneralUtil.sendMessage(
                    sender,
                    "${amount}${econ.currencyNameSingular()}の送金に失敗しました。",
                    MessagePrefix.ERROR
                )
                return
            }
        } else {
            GeneralUtil.sendMessage(
                sender,
                "所持金が${amount}${econ.currencyNameSingular()}足りません。",
                MessagePrefix.ERROR
            )
            return
        }
    }
}