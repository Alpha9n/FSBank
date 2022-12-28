package pro.freeserver.alphakun.plugin.fsbank.command

import dev.rollczi.litecommands.argument.Arg
import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import pro.freeserver.alphakun.plugin.fsbank.utils.MessagePrefix

@Route(name = "fsbank", aliases = ["money"])
class Help {
    private var gu = GeneralUtil
    @Execute(aliases = ["help"])
    fun defaultHelp(sender: CommandSender) {
        if (sender is Player) {
            defaultMessage(sender)
            if (sender.isOp) {
                adminMessage(sender)
            }
        }
    }

    private fun defaultMessage(sender: Player) {
        gu.sendMessage(sender, "=======§b[§fFSBank ヘルプ§b]§f=======")
        gu.sendMessage(sender, "/fsbank [help/無し] - ヘルプ画面を表示します")
        gu.sendMessage(sender, "/fsbank show [MCID] - ユーザーの所持金を表示します")
    }

    private fun adminMessage(sender: Player) {
        gu.sendMessage(sender, "/fsbank hogehoge - ほげほげ")
    }
}