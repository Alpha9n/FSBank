package pro.freeserver.alphakun.plugin.fsbank.handler

import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.client
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.mainConfig
import pro.freeserver.alphakun.plugin.fsbank.consts.FreeserverUser
import pro.freeserver.alphakun.plugin.fsbank.consts.WalletBalances
import pro.freeserver.alphakun.plugin.fsbank.utils.GeneralUtil
import pro.freeserver.alphakun.plugin.fsbank.utils.TableName
import java.util.*

class TransactionHandler {

    fun getDiscordID(uuid: UUID? = UUID.randomUUID()):String? {
        if (uuid == null) return null
        return try {
            var response = client
                .from<FreeserverUser>(TableName.FREESERVER_USER.text)
                .select("*")
                .eq("mcuuid", uuid)
                .limit(1)
                .single()
                .executeAndGetSingle<FreeserverUser>()
            response.discord_id
        }catch (e: Exception) {
            null
        }
    }

    fun getWalletAccount(uuid: UUID?): WalletBalances? {
        if (uuid == null) return null
        return try {
            val walID = getFSUser(uuid).wallet_id ?: return null
            client
                .from<WalletBalances>(TableName.WALLET_BALANCES.text)
                .select("*")
                .eq("id", walID)
                .limit(1)
                .single()
                .executeAndGetSingle()
        } catch (e: Exception) {
            null
        }
    }

    fun hasWalletAccount(uuid: UUID?): Boolean {
        if(uuid == null) return false
        getWalletAccount(uuid)?: return false
        return true
    }

    fun getUserWalletAmount(uuid: UUID? = UUID.randomUUID()): Double? {
        if(uuid == null) return null
        return GeneralUtil().toDouble(getWalletAccount(uuid)?.balance?: return null)
    }

    fun getUserWalletAmountAsLong(uuid: UUID? = UUID.randomUUID()): Long? {
        if(uuid == null) return null
        return getWalletAccount(uuid)?.balance?: return null
    }

    fun hasWalletAmount(uuid: UUID? = UUID.randomUUID(), amount: Double): Boolean {
        return amount >= (getUserWalletAmount(uuid)?: 0.00)
    }

    fun setWalletAmount(uuid: UUID? = UUID.randomUUID(), amount: Double): Boolean {
        if (uuid == null) return false
        var longAmount = GeneralUtil().toLong(amount)
        var putAmount = (getUserWalletAmountAsLong(uuid)?: 0) + longAmount
        return try {
            client
                .from<WalletBalances>(TableName.WALLET_BALANCES.text)
                .update(mapOf("balance" to putAmount))
                .eq("mcuuid", uuid)
                .single()
                .execute()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun depositPlayer(uuid: UUID? = UUID.randomUUID(), amount: Double): Boolean {
        return setWalletAmount(uuid, amount)
    }

    fun withdrawPlayer(uuid: UUID? = UUID.randomUUID(), amount: Double): Boolean {
        return setWalletAmount(uuid, -amount)
    }

    fun createWalletAccount(uuid: UUID? = UUID.randomUUID()): Boolean {
        if (!hasWalletAccount(uuid)) {
            return try {
                val defaultBalance = GeneralUtil().toLong(FSBank.mainConfig.defaultBalance)
                val walletUUID = UUID.randomUUID()
                var response = client
                    .from<WalletBalances>(TableName.WALLET_BALANCES.text)
                    .insert(WalletBalances(id = walletUUID, balance = defaultBalance, mcuuid = uuid!!))
                    .execute()
                println(response.body)
                response = client
                    .from<FreeserverUser>(TableName.FREESERVER_USER.text)
                    .update(mapOf("wallet_id" to walletUUID))
                    .eq("mcuuid", uuid)
                    .single()
                    .execute()
                println(response.body)
                    true
            } catch (e: Exception) {
                false
            }
        }
        return false
    }

    fun getFSUser(uuid: UUID): FreeserverUser {
        var response = client
            .from<FreeserverUser>(TableName.FREESERVER_USER.text)
            .select("*")
            .eq("mcuuid", uuid)
            .limit(1)
            .single()
            .executeAndGetSingle<FreeserverUser>()
        return response
    }
}