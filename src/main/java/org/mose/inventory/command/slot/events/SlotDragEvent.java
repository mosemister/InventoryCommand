package org.mose.inventory.command.slot.events;

import org.bukkit.event.inventory.InventoryDragEvent;
import org.mose.inventory.command.InventoryCommandContext;
import org.mose.inventory.command.slot.Slot;

public class SlotDragEvent extends AbstractSlotEvent<InventoryDragEvent> implements SlotEvent<InventoryDragEvent> {

    public SlotDragEvent(InventoryCommandContext context, InventoryDragEvent event, Slot<?> slot) {
        super(context, event, slot);
    }
}
