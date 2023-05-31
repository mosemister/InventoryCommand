package org.mose.inventory.command.slot;

import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.mose.inventory.command.slot.events.SlotClickEvent;
import org.mose.inventory.command.slot.events.SlotDragEvent;
import org.mose.inventory.command.slot.events.SlotEvent;

import java.util.Collection;
import java.util.OptionalInt;
import java.util.function.Consumer;

public interface Slot<TValue> {

    ItemStack createStack();

    Collection<Consumer<SlotClickEvent>> onClick();

    Collection<Consumer<SlotDragEvent>> onDrag();

    Collection<Consumer<SlotEvent<? extends InventoryEvent>>> onInteract();

    OptionalInt preferredPosition();

    TValue value();

    String valueDisplay();


}
