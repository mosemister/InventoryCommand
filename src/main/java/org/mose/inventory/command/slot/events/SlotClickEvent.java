package org.mose.inventory.command.slot.events;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.slot.Slot;

public class SlotClickEvent extends AbstractSlotEvent<InventoryClickEvent> implements SlotEvent<InventoryClickEvent> {

    public SlotClickEvent(InventoryCommandContext context, InventoryClickEvent event, Slot<?> slot) {
        super(context, event, slot);
    }
}
