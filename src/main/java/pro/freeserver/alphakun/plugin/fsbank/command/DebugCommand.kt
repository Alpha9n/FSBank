package pro.freeserver.alphakun.plugin.fsbank.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.utils.MessagePrefix
import pro.freeserver.alphakun.plugin.fsbank.handler.TransactionHandler
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil

class DebugCommand: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender.isOp) {
            if (command.label.equals("debug",true)) {
                val trans = TransactionHandler()
                var message = MessagePrefix.ERROR.text + "例外が発生しました"
                if (args?.get(0).equals("getuserwalletamount",true)) {
                    var playerID:String = ""
                    if(args?.size!! <= 1 && sender is Player) {
                        playerID = sender.uniqueId.toString()
                        message = MessagePrefix.INFO.text + "${sender.name}の所持金"
                    } else if (Bukkit.getPlayerUniqueId(args[1]) != null) {
                        playerID = Bukkit.getPlayerUniqueId(args[1]).toString()
                        message = MessagePrefix.INFO.text + "${args[1]}の所持金"
                    }
                    val amount = GeneralUtil().currencyFormat(trans.getUserWalletAmount(Bukkit.getPlayerUniqueId(playerID))?:0.00)
                    if (amount == null) {
                        sender.sendMessage(MessagePrefix.ERROR.text + "ユーザーが見つかりませんでした")
                        return false
                    }
                    sender.sendMessage(message + amount)
                    return true
                }


                // TODO("Discord表示名の取得表示を追加する")
                if (args?.get(0).equals("getdiscordid", true)) {
                    var playerID: String = ""
                    if(args?.size!! <= 1 && sender is Player) {
                        playerID = sender.uniqueId.toString()
                        message = MessagePrefix.INFO.text + "${sender.name}のDiscordID:"
                    } else if (Bukkit.getPlayerUniqueId(args[1]) != null) {
                        playerID = Bukkit.getPlayerUniqueId(args[1]).toString()
                        message = MessagePrefix.INFO.text + "${args[1]}のDiscordID:"
                    }
                    val discordID = trans.getDiscordID(Bukkit.getPlayerUniqueId(playerID))
                    if (discordID == null) {
                        sender.sendMessage(MessagePrefix.ERROR.text + "ユーザーが見つかりませんでした")
                        return false
                    }
                    sender.sendMessage(message + discordID)
                    return true
                }
            }
            return true
        }
        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>?): MutableList<String>? {
        if (command.label.equals("debug", true)) {
            if (args?.size!! == 1) {
                return mutableListOf("getuserwalletamount", "getdiscordid")
            }
        }
        return null
    }
}