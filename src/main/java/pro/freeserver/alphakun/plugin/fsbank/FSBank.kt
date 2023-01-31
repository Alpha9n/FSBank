package pro.freeserver.alphakun.plugin.fsbank

import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.adventure.paper.LitePaperAdventureFactory
import io.supabase.postgrest.PostgrestDefaultClient
import net.milkbowl.vault.economy.Economy
import org.bukkit.command.CommandSender
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import pro.freeserver.alphakun.plugin.fsbank.command.FSBankCommand
import pro.freeserver.alphakun.plugin.fsbank.config.MainConfig
import pro.freeserver.alphakun.plugin.fsbank.databases.Postgrest
import pro.freeserver.alphakun.plugin.fsbank.economy.VaultEconomy
import pro.freeserver.alphakun.plugin.fsbank.handler.event.EventTest


class FSBank : JavaPlugin() {
    private lateinit var liteCommand: LiteCommands<CommandSender>
    override fun onEnable() {
        saveDefaultConfig()
        fsBank = this
        mainConfig = MainConfig()
        println("bankName: " + mainConfig.bankName + " host: " + mainConfig.host)
        client = Postgrest(mainConfig.host, mainConfig.serviceKey).getClient()
        hookVault()
        setupVault()
        loadCommand()
        registerEvent()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun loadCommand() {
        this.liteCommand = LitePaperAdventureFactory.builder(server, "FSBank")
            .commandInstance(
                FSBankCommand()
            ).register()
    }

    private fun registerEvent() {
        server.pluginManager.registerEvents(EventTest(), this)
    }

    private fun hookVault() {
        server.servicesManager.register(Economy::class.java, VaultEconomy(), fsBank, ServicePriority.Highest)
    }

    private fun setupVault() {
        if (!setupEconomy()) {
            logger.severe("${description.name} - Disabled due to no Vault dependency found!")
            server.pluginManager.disablePlugin(this)
            return
        }
    }

    private fun setupEconomy(): Boolean{
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }
        val rsp: RegisteredServiceProvider<Economy> = server.servicesManager.getRegistration(Economy::class.java)?: return false
        econ = rsp.provider
        return true
    }

    companion object {
        lateinit var fsBank: FSBank
        lateinit var mainConfig: MainConfig
        lateinit var client: PostgrestDefaultClient
        lateinit var econ: Economy
    }
}