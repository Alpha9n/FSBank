package pro.freeserver.alphakun.plugin.fsbank.config

import org.bukkit.Bukkit
import pro.freeserver.alphakun.plugin.fsbank.FSBank.Companion.fsBank

class MainConfig {
    private val config = fsBank.config
    private val sqlPath = "sql-conf."
    private val bankPath = "bank-conf."
    private val currencyPath = "currency-conf."
    private var configVer = 0

    var host: String = "null"
    var serviceKey: String = "null"
    var defaultBalance: Double = 1000.00
    var bankName: String = "Bank"
    var currencyName: String = "YEN"
    var currencyPlural: String = "YEN"
    var currencyFormat: String = "{major}.{minor}{plural}"

    init {
        this.configVer = config.getInt("config-version")
        if (configVer == 2) {
            if (config.contains(sqlPath + "host") || config.contains(sqlPath + "serviceKey")) {
                host = config.getString(sqlPath + "host")?:host
                serviceKey = config.getString(sqlPath + "service-key")?:serviceKey
            } else {
                Bukkit.getLogger().warning("Config.ymlにhostかservice-keyが存在しません。")
            }
            defaultBalance = config.getDouble(currencyPath + "default-balance")
            bankName = config.getString(bankPath + "bank-name")?:bankName
            currencyName = config.getString(currencyPath + "currency-name")?:currencyName
            currencyPlural = config.getString(currencyPath + "currency-plural")?:currencyPlural
            currencyFormat = config.getString(currencyPath + "currency-format")?:currencyFormat
        } else {
            Bukkit.getLogger().warning("Config.ymlのバージョンが不正です。削除して再起動してください。")
        }
    }
}