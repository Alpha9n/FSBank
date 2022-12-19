package pro.freeserver.alphakun.plugin.fsbank.api

import io.supabase.postgrest.PostgrestDefaultClient
import org.bukkit.Bukkit
import pro.freeserver.alphakun.plugin.fsbank.FSBank
import pro.freeserver.alphakun.plugin.fsbank.api.JsonUtil.fromJson
import pro.freeserver.alphakun.plugin.fsbank.type.FreeserverUser
import java.net.URI

class Postgrest(uri: String, serviceKey: String) {
    private val client: PostgrestDefaultClient
    init {
        try {
            client = PostgrestDefaultClient(
                URI(uri),
                mapOf("Authorization" to "Bearer $serviceKey", "apikey" to serviceKey)
            )
        } catch (e: Exception) {
            throw Error(MessagePrefix.ERROR.text + "ホストに接続できませんでした。")
        }
    }

    fun getClient(): PostgrestDefaultClient {
        val message = client
            .from<FreeserverUser>("freeserver_user")
            .select()
            .eq("mcuuid", "a1f8207c-dbaa-426d-92a4-38c6d0f3c570")
            .limit(1)
            .single()
            .executeAndGetSingle<FreeserverUser>()
        println(message.discord_id)
        return client
    }
}