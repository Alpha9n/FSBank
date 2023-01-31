package pro.freeserver.alphakun.plugin.fsbank.command

import net.milkbowl.vault.economy.Economy
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import pro.freeserver.alphakun.plugin.fsbank.utils.MessagePrefix

object Show {
    fun defaultShow(sender: Player, target: Player?, econ: Economy) {
        if (target == null) {
            GeneralUtil.sendMessage(sender, "プレイヤーが見つかりません。", MessagePrefix.ERROR)
            return
        }
        if (!econ.hasAccount(sender)) {
            if(econ.createPlayerAccount(sender)) {
                GeneralUtil.sendMessage(sender, "${sender.name}の口座を作成しました。")
            } else {
                GeneralUtil.sendMessage(sender, "${sender.name}の口座を作成できませんでした。", MessagePrefix.ERROR)
                return
            }

        }
        val balance = econ.getBalance(target)
        GeneralUtil.sendMessage(sender, "${target.name}の所持金は${balance}${econ.currencyNameSingular()}です。")
    }
}