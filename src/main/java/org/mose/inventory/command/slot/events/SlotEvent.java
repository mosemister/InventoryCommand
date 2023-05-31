package org.mose.inventory.command.slot.events;

import org.bukkit.event.inventory.InventoryEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.screen.ScreenSnapshot;
import org.mose.inventory.command.slot.Slot;

public interface SlotEvent<TEvent extends InventoryEvent> {

    TEvent bukkitEvent();

    InventoryCommandContext context();

    default void nextScreen(ScreenSnapshot<?> newScreen) {
        context().nextScreen(slot().value(), newScreen);
    }

    Slot<?> slot();

}
