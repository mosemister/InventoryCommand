package org.mose.inventory.command.slot.events;

import org.bukkit.event.inventory.InventoryEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.slot.Slot;

class AbstractSlotEvent<TEvent extends InventoryEvent> implements SlotEvent<TEvent> {

    private final InventoryCommandContext context;
    private final TEvent event;
    private final Slot<?> slot;

    AbstractSlotEvent(InventoryCommandContext context, TEvent event, Slot<?> slot) {
        this.context = context;
        this.event = event;
        this.slot = slot;
    }

    @Override
    public TEvent bukkitEvent() {
        return this.event;
    }

    @Override
    public InventoryCommandContext context() {
        return this.context;
    }

    @Override
    public Slot<?> slot() {
        return this.slot;
    }
}
