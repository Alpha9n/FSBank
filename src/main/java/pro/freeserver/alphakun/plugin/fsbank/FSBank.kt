package pro.freeserver.alphakun.plugin.fsbank

import io.supabase.postgrest.PostgrestDefaultClient
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.alphakun.plugin.fsbank.api.LoadConfig
import pro.freeserver.alphakun.plugin.fsbank.api.Postgrest

class FSBank : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        fsBank = this
        loadConfig = LoadConfig()
        client = Postgrest(loadConfig.host, loadConfig.serviceKey).getClient()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        lateinit var fsBank: FSBank
        lateinit var loadConfig: LoadConfig
        lateinit var client: PostgrestDefaultClient
    }
}