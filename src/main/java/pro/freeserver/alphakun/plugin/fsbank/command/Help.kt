package pro.freeserver.alphakun.plugin.fsbank.command

import org.bukkit.Bukkit.getServer
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil

object Help {
    private var gu = GeneralUtil
    fun defaultMessage(sender: Player) {
        gu.sendMessage(sender, "=======§b[§fFSBank ヘルプ§b]§f=======")
        gu.sendMessage(sender, "/fsbank help - ヘルプ画面を表示します")
        gu.sendMessage(sender, "/fsbank show [MCID] - ユーザーの所持金を表示します")
        gu.sendMessage(sender, "/fsbank pay [MCID] [金額] - ユーザーに金額を送金します")
    }

    fun adminMessage(sender: Player) {
        gu.sendMessage(sender, "/fsbank hogehoge - ほげほげ")
    }
}