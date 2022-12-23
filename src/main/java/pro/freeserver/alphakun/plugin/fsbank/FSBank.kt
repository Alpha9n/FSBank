package pro.freeserver.alphakun.plugin.fsbank

import io.supabase.postgrest.PostgrestDefaultClient
import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.alphakun.plugin.fsbank.config.MainConfig
import pro.freeserver.alphakun.plugin.fsbank.databases.Postgrest
import pro.freeserver.alphakun.plugin.fsbank.command.DebugCommand
import pro.freeserver.alphakun.plugin.fsbank.economy.VaultEconomy


class FSBank : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        fsBank = this
        mainConfig = MainConfig()
        println(mainConfig.bankName + "host: " + mainConfig.host)
        client = Postgrest(mainConfig.host, mainConfig.serviceKey).getClient()
        loadCommand()
        hookVault()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun loadCommand() {
        getCommand("debug")?.setExecutor(DebugCommand())
    }

    fun hookVault() {
        server.servicesManager.register(Economy::class.java, VaultEconomy(), fsBank, ServicePriority.Highest)
    }

    companion object {
        lateinit var fsBank: FSBank
        lateinit var mainConfig: MainConfig
        lateinit var client: PostgrestDefaultClient
    }
}