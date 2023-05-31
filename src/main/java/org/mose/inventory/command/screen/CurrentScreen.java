package org.mose.inventory.command.screen;

import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.mose.inventory.command.slot.Slot;

public interface CurrentScreen<TValue> extends Screen<TValue> {

    ScreenSnapshot<TValue> basedOn();

    InventoryView bukkitInventory();

    Slot<TValue> slot(int slotIndex, ItemStack itemInSlot);

}
