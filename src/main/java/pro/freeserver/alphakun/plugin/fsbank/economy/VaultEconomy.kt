package pro.freeserver.alphakun.plugin.fsbank.economy

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.handler.TransactionHandler
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import java.util.*

class VaultEconomy(): Economy {
    private val trans = TransactionHandler()
    private val gUtil = GeneralUtil()
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getName(): String {
        return "FSBank"
    }

    override fun hasBankSupport(): Boolean {
        return true
    }

    override fun fractionalDigits(): Int {
        return GeneralUtil.FRACTIONAL_DIGITS
    }

    override fun format(amount: Double): String {
        return gUtil.currencyFormat(gUtil.toLong(amount))
    }

    override fun currencyNamePlural(): String {
        return FSBank.mainConfig.currencyPlural
    }

    override fun currencyNameSingular(): String {
        return FSBank.mainConfig.currencyPlural
    }

    override fun hasAccount(playerName: String?): Boolean {
        return trans.hasWalletAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun hasAccount(player: OfflinePlayer?): Boolean {
        return trans.hasWalletAccount(player!!.uniqueId)
    }

    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        return trans.hasWalletAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return trans.hasWalletAccount(player!!.uniqueId)
    }

    override fun getBalance(playerName: String?): Double {
        return trans.getUserWalletAmount(Bukkit.getPlayerUniqueId(playerName?:""))?: 0.00
    }

    override fun getBalance(player: OfflinePlayer?): Double {
        return trans.getUserWalletAmount(player?.uniqueId)?: 0.00
    }

    override fun getBalance(playerName: String?, world: String?): Double {
        return trans.getUserWalletAmount(Bukkit.getPlayerUniqueId(playerName?:""))?: 0.00
    }

    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        return trans.getUserWalletAmount(player?.uniqueId)?: 0.00
    }

    override fun has(playerName: String?, amount: Double): Boolean {
        return trans.hasWalletAmount(Bukkit.getPlayerUniqueId(playerName?: ""),amount)
    }

    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        return trans.hasWalletAmount(player?.uniqueId,amount)
    }

    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        return trans.hasWalletAmount(Bukkit.getPlayerUniqueId(playerName?: ""), amount)
    }

    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        return trans.hasWalletAmount(player?.uniqueId, amount)
    }

    private fun withdrawPlayer(player: UUID?, amount: Double): EconomyResponse {
        if (amount < 0) {
            return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds")
        }
        if (trans.withdrawPlayer(player, amount)) {
            return EconomyResponse(0.0, trans.getUserWalletAmount(player)?:0.0, EconomyResponse.ResponseType.SUCCESS, "OK")
        } else {
            return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Account does not exist")
        }
    }

    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        return withdrawPlayer(Bukkit.getPlayerUniqueId(playerName?:""), amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        return withdrawPlayer(player?.uniqueId, amount)
    }

    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        return withdrawPlayer(Bukkit.getPlayerUniqueId(playerName?:""), amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        return withdrawPlayer(player?.uniqueId, amount)
    }

    private fun depositPlayer(player: UUID?, amount: Double): EconomyResponse {
        if (amount < 0) {
            return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds")
        }
        if (trans.depositPlayer(player, amount)) {
            return EconomyResponse(0.0, trans.getUserWalletAmount(player)?:0.0, EconomyResponse.ResponseType.SUCCESS, "OK")
        } else {
            return EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "Account does not exist")
        }
    }

    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        return depositPlayer(Bukkit.getPlayerUniqueId(playerName?: ""),amount)
    }

    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        return depositPlayer(player?.uniqueId, amount)
    }

    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        return depositPlayer(Bukkit.getPlayerUniqueId(playerName?: ""),amount)
    }

    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        return depositPlayer(player?.uniqueId, amount)
    }
    override fun createPlayerAccount(playerName: String?): Boolean {
        return trans.createWalletAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        return trans.createWalletAccount(player?.uniqueId)
    }

    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        return trans.createWalletAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return trans.createWalletAccount(player?.uniqueId)
    }



    // TODO("銀行機能実装は財布実装が終わってから")

    override fun createBank(name: String?, player: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun createBank(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun deleteBank(name: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankBalance(name: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankHas(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankWithdraw(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun bankDeposit(name: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankOwner(name: String?, playerName: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankOwner(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankMember(name: String?, playerName: String?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun isBankMember(name: String?, player: OfflinePlayer?): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun getBanks(): MutableList<String> {
        TODO("Not yet implemented")
    }

}