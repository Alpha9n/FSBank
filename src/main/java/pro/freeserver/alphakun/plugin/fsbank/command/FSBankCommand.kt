package pro.freeserver.alphakun.plugin.fsbank.command

import dev.rollczi.litecommands.argument.Arg
import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import java.math.BigDecimal

@Route(name = "fsbank", aliases = ["money"])
class FSBankCommand {
    var econ = FSBank.econ

    @Execute(aliases = ["help"])
    fun help(sender: CommandSender) {
        if (sender is Player) {
            Help.defaultMessage(sender)
            if (sender.isOp) {
                Help.adminMessage(sender)
            }
        }
    }

    @Execute(route= "pay")
    fun pay(sender: Player, @Arg dist: OfflinePlayer, @Arg amount: BigDecimal) {
        Pay.defaultPay(sender,dist, GeneralUtil().toDouble(amount.toLong()),econ)
    }
}
