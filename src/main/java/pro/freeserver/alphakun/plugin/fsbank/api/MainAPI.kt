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

enum class MessagePrefix(prefix: String) {
}