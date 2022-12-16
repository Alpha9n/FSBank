package pro.freeserver.alphakun.plugin.fsbank.api

import org.bukkit.Bukkit
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.fsBank

class LoadConfig {
    private val config = fsBank.config
    var host: String = "null"
    var serviceKey: String = "null"
    init {
        if (config.contains("host") || config.contains("serviceKey")) {
            host = config.getString("host")?:"null"
            serviceKey = config.getString("service-key")?:"null"
        } else {
            Bukkit.getLogger().warning("Config.ymlにhostかservice-keyが存在しません。")
        }
    }

    fun getHost(): String {
        return host
    }

    fun getServiceKey(): String {
        return serviceKey
    }

}