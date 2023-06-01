package org.mose.inventory.command.slot.events;

import org.bukkit.event.inventory.InventoryEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.screen.ScreenSnapshot;
import org.mose.inventory.command.slot.Slot;

public interface SlotEvent<TEvent extends InventoryEvent> {

    TEvent bukkitEvent();

    default void complete() {
        this.context().complete(this.slot().value());
    }

    InventoryCommandContext context();

    default void nextScreen(ScreenSnapshot<?> newScreen) {
        this.context().nextScreen(this.slot().value(), newScreen);
    }

    Slot<?> slot();

}
