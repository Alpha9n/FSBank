package pro.freeserver.alphakun.plugin.fsbank.api

import org.bukkit.Bukkit
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.fsBank

class LoadConfig {
    private val config = fsBank.config
    private val configVer = config.getInt("config-version")
    private val sqlPath = "sql-conf."
    private val bankPath = "bank-conf."
    private val currencyPath = "currency-conf."

    var host: String = "null"
    var serviceKey: String = "null"
    var bankName: String = "Bank"
    var currencyName: String = "YEN"
    var currencyPlural: String = "YEN"
    var currencyFormat: String = "0"

    init {
        if (configVer == 1) {
            if (config.contains(sqlPath + "host") || config.contains(sqlPath + "serviceKey")) {
                host = config.getString(sqlPath + "host")?:"null"
                serviceKey = config.getString(sqlPath + "service-key")?:"null"
            } else {
                Bukkit.getLogger().warning("Config.ymlにhostかservice-keyが存在しません。")
            }
            bankName = config.getString(bankPath + "bank-name")?:"Bank"
            currencyName = config.getString(currencyPath + "currency-name")?:"YEN"
            currencyPlural = config.getString(currencyPath + "currency-plural")?:"YEN"
            currencyFormat = config.getString(currencyPath + "currency-format")?:"0"
        } else {
            Bukkit.getLogger().warning("Config.ymlのバージョンが不正です。削除して再起動してください。")
        }
    }
}