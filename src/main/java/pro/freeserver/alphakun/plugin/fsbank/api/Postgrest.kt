package pro.freeserver.alphakun.plugin.fsbank.api

import io.supabase.postgrest.PostgrestDefaultClient
import java.net.URI

class Postgrest(uri: String, serviceKey: String) {
    private val client: PostgrestDefaultClient
    init {
        try {
            client = PostgrestDefaultClient(
                URI.create(uri),
                mapOf("Authorization" to "Bearer $serviceKey", "apikey" to serviceKey)
            )
        } catch (e: Exception) {
            throw e
        }
    }

    fun getClient(): PostgrestDefaultClient {
        return client
    }
}