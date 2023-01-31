package pro.freeserver.alphakun.plugin.fsbank.command

import dev.rollczi.litecommands.argument.Arg
import dev.rollczi.litecommands.argument.Name
import dev.rollczi.litecommands.argument.option.Opt
import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import panda.std.Option
import pro.freeserver.alphakun.plugin.fsbank.FSBank

@Route(name = "fsbank", aliases = ["money"])
class FSBankCommand {
    var econ = FSBank.econ

    @Execute(min = 0, max = 0)
    fun selfShow(sender: CommandSender) {
        show(sender, Option.of(sender.name))
    }

    @Execute(route = "show", min = 0, max = 1)
    fun show(sender: CommandSender, @Opt @Name("player") target: Option<String>) {
        if (sender !is Player) {
            sender.sendMessage("このコマンドはプレイヤーのみ実行できます。")
            return
        }
        Show.defaultShow(
            sender,
            Bukkit.getPlayer(target.orElseGet(sender.name)),
            econ
        )
    }

    @Execute(route = "help")
    fun help(sender: CommandSender) {
        if (sender is Player) {
            Help.defaultMessage(sender)
            if (sender.isOp) {
                Help.adminMessage(sender)
            }
        }
    }

    @Execute(route= "pay", required = 2)
    fun pay(sender: CommandSender, @Arg @Name("player") dist: String, @Arg @Name("amount") amount: Double) {
        try{
            Pay.defaultPay(sender as Player, Bukkit.getOfflinePlayer(dist), amount, econ)
        }catch (e: Exception) {
            Bukkit.getLogger().severe(e.toString())
        }
    }
}
