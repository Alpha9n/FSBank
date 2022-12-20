package pro.freeserver.alphakun.plugin.fsbank

import io.supabase.postgrest.PostgrestDefaultClient
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.alphakun.plugin.fsbank.api.MainConfig
import pro.freeserver.alphakun.plugin.fsbank.api.Postgrest
import pro.freeserver.alphakun.plugin.fsbank.command.DebugCommand


class FSBank : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        fsBank = this
        mainConfig = MainConfig()
        client = Postgrest(mainConfig.host, mainConfig.serviceKey).getClient()
        loadCommand()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun loadCommand() {
        getCommand("debug")?.setExecutor(DebugCommand())
    }

    companion object {
        lateinit var fsBank: FSBank
        lateinit var mainConfig: MainConfig
        lateinit var client: PostgrestDefaultClient
    }
}