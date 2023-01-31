package pro.freeserver.alphakun.plugin.fsbank.events

import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PaymentEvent(sender: OfflinePlayer, destination: OfflinePlayer, amount: Double) :Event(), Cancellable {
    var sender: OfflinePlayer = sender
    var destination: OfflinePlayer = destination
    var amount: Double = amount
    private var isCancelled: Boolean = false
    private val HANDLERS = HandlerList()

    init {
        this.isCancelled = false
        this.sender = sender
        this.destination = destination
        this.amount = amount
    }

    companion object {
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HandlerList()
        }
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    override fun isCancelled(): Boolean {
        return this.isCancelled
    }

    override fun setCancelled(cancel: Boolean) {
        this.isCancelled = cancel
    }
}