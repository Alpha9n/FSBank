package pro.freeserver.alphakun.plugin.fsbank.databases

import io.supabase.postgrest.PostgrestDefaultClient
import pro.freeserver.alphakun.plugin.fsbank.consts.FreeserverUser
import pro.freeserver.alphakun.plugin.fsbank.utils.MessagePrefix
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
        return client
    }
}