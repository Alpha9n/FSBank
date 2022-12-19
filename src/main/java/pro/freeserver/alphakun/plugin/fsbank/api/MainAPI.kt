package pro.freeserver.alphakun.plugin.fsbank.api

class MainAPI {

}

enum class TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER
}

enum class TransactionStatus {
    SUCCESS,
    FAILED,
    PENDING
}

enum class MessagePrefix(val text: String) {
    INFO("§a[§fFSBank§a]§f:§r"),
    WARN("§e[§fFSBank§e]§f:§r"),
    ERROR("§c[§fFSBank§c]§f:§r")
}

enum class TableName(val text: String) {
    FREESERVER_USER("freeserver_user"),
    BANK_BALANCES("bank_balances"),
    WALLET_BALANCES("wallet_balances")
}