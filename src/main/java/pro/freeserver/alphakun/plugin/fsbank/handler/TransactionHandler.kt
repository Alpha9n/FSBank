package pro.freeserver.alphakun.plugin.fsbank.handler

import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.api.TableName
import pro.freeserver.alphakun.plugin.fsbank.type.FreeserverUser
import pro.freeserver.alphakun.plugin.fsbank.type.WalletBalances

class TransactionHandler {
    fun getUserWalletAmount(uuid: String = ""): String? {
        try {
            var response = FSBank.client
                .from<WalletBalances>(TableName.WALLET_BALANCES.text)
                .select("*")
                .eq("mcuuid", uuid)
                .limit(1)
                .single()
                .executeAndGetSingle<WalletBalances>()
            return response.balance.toString()
        }catch (e: Exception) {
            return null
        }
    }

    fun getDiscordID(uuid: String = ""):String? {
        try {
            var response = FSBank.client
                .from<FreeserverUser>(TableName.FREESERVER_USER.text)
                .select("*")
                .eq("mcuuid", uuid)
                .limit(1)
                .single()
                .executeAndGetSingle<FreeserverUser>()
            return response.discord_id
        }catch (e: Exception) {
            return null
        }
    }
}