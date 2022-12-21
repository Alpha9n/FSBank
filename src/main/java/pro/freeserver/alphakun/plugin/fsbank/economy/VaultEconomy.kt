package pro.freeserver.alphakun.plugin.fsbank.economy

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.api.MainAPI
import pro.freeserver.alphakun.plugin.fsbank.handler.TransactionHandler

class VaultEconomy(): Economy {
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
        return MainAPI.FRACTIONAL_DIGITS
    }

    override fun format(amount: Double): String {
        return MainAPI().currencyFormat(amount)
    }

    override fun currencyNamePlural(): String {
        return FSBank.mainConfig.currencyPlural
    }

    override fun currencyNameSingular(): String {
        return FSBank.mainConfig.currencyName
    }

    override fun hasAccount(playerName: String?): Boolean {
        return TransactionHandler.hasAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun hasAccount(player: OfflinePlayer?): Boolean {
        return TransactionHandler.hasAccount(player!!.uniqueId)
    }

    override fun hasAccount(playerName: String?, worldName: String?): Boolean {
        return TransactionHandler.hasAccount(Bukkit.getPlayerUniqueId(playerName?:""))
    }

    override fun hasAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        return TransactionHandler.hasAccount(player!!.uniqueId)
    }

    override fun getBalance(playerName: String?): Double {
        TODO("Not yet implemented")
    }

    override fun getBalance(player: OfflinePlayer?): Double {
        TODO("Not yet implemented")
    }

    override fun getBalance(playerName: String?, world: String?): Double {
        TODO("Not yet implemented")
    }

    override fun getBalance(player: OfflinePlayer?, world: String?): Double {
        TODO("Not yet implemented")
    }

    override fun has(playerName: String?, amount: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun has(player: OfflinePlayer?, amount: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun has(playerName: String?, worldName: String?, amount: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun has(player: OfflinePlayer?, worldName: String?, amount: Double): Boolean {
        TODO("Not yet implemented")
    }

    override fun withdrawPlayer(playerName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun withdrawPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun withdrawPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun withdrawPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(playerName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(player: OfflinePlayer?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(playerName: String?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

    override fun depositPlayer(player: OfflinePlayer?, worldName: String?, amount: Double): EconomyResponse {
        TODO("Not yet implemented")
    }

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

    override fun createPlayerAccount(playerName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createPlayerAccount(player: OfflinePlayer?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createPlayerAccount(playerName: String?, worldName: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun createPlayerAccount(player: OfflinePlayer?, worldName: String?): Boolean {
        TODO("Not yet implemented")
    }
}