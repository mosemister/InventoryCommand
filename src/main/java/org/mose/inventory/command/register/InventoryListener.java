package org.mose.inventory.command.register;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.screen.CurrentScreen;
import org.mose.inventory.command.slot.Slot;
import org.mose.inventory.command.slot.events.SlotClickEvent;
import org.mose.inventory.command.slot.events.SlotEvent;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InventoryListener implements Listener {

    private final Supplier<InventoryContextManager> manager;

    public InventoryListener(Supplier<InventoryContextManager> manager) {
        this.manager = manager;
    }

    private InventoryContextManager manager() {
        return this.manager.get();
    }

    @EventHandler
    public <TValue> void onInventoryClick(InventoryClickEvent event) {
        InventoryContextManager manager = this.manager();
        HumanEntity human = event.getWhoClicked();
        if (!(human instanceof Player player)) {
            return;
        }
        Optional<InventoryCommandContext> opContext = manager.runningContext(player);
        if (opContext.isEmpty()) {
            return;
        }
        InventoryCommandContext context = opContext.get();
        CurrentScreen<TValue> currentScreen = (CurrentScreen<TValue>) context.currentScreen();
        Slot<TValue> slot = currentScreen.slot(event.getRawSlot(), event.getCurrentItem());

        SlotClickEvent slotEvent = new SlotClickEvent(context, event, slot);
        for (Consumer<SlotClickEvent> consumer : slot.onClick()) {
            consumer.accept(slotEvent);
        }
        this.onInventoryInteract(slotEvent);
    }

    private void onInventoryInteract(SlotEvent<?> interact) {
        for (Consumer<SlotEvent<? extends InventoryEvent>> consumer : interact.slot().onInteract()) {
            consumer.accept(interact);
        }
    }
}
