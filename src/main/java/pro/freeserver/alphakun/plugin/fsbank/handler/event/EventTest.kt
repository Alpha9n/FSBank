package pro.freeserver.alphakun.plugin.fsbank.handler.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pro.freeserver.alphakun.plugin.fsbank.events.PaymentEvent

class EventTest: Listener {
    @EventHandler
    fun onPayment(e: PaymentEvent) {
        e.sender.player?.sendMessage("PaymentEventが発生しました。")
    }
}