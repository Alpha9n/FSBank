package pro.freeserver.alphakun.plugin.fsbank.command

import dev.rollczi.litecommands.argument.Arg
import dev.rollczi.litecommands.argument.Name
import dev.rollczi.litecommands.argument.option.Opt
import dev.rollczi.litecommands.command.async.Async
import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import panda.std.Option
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.econ
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import kotlin.math.min

@Route(name = "FSBank", aliases = ["money"])
class Show {
    // TODO("コマンド実行時にエラーが吐かれる")
    @Async
    @Execute(route = "show")
    fun show(sender: Player, @Arg text: String) {
        if(sender.isOp) {
            buildMessage(sender, text)
        } else {
            buildMessage(sender, sender.name)
        }
    }

    private fun buildMessage(sender: Player, userName: String) {
        val format = econ.format(econ.getBalance(userName))
        GeneralUtil.sendMessage(sender, userName + "の所持金: " + format)
    }
}