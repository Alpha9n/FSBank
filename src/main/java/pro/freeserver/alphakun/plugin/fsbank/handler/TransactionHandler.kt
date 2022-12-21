package pro.freeserver.alphakun.plugin.fsbank.handler

import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.client
import pro.freeserver.alphakun.plugin.fsbank.utils.TableName
import pro.freeserver.alphakun.plugin.fsbank.consts.FreeserverUser
import pro.freeserver.alphakun.plugin.fsbank.consts.WalletBalances
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
            client
                .from<WalletBalances>(TableName.WALLET_BALANCES.text)
                .select("*")
                .eq("mcuuid", uuid)
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
        return getWalletAccount(uuid)?.balance?.toDouble()
    }

    fun hasWalletAmount(uuid: UUID? = UUID.randomUUID(), amount: Double): Boolean {
        return amount >= (getUserWalletAmount(uuid)?: 0.00)
    }
}