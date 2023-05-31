package org.mose.inventory.command.slot;

import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.mose.inventory.command.slot.events.SlotClickEvent;
import org.mose.inventory.command.slot.events.SlotDragEvent;
import org.mose.inventory.command.slot.events.SlotEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class SimpleSlot<TValue> implements Slot<TValue> {

    private final TValue value;
    private final Function<TValue, String> valueString;
    private final Supplier<ItemStack> createItem;
    private final Integer slot;
    private Collection<Consumer<SlotClickEvent>> onClick;
    private Collection<Consumer<SlotDragEvent>> onDrag;
    private Collection<Consumer<SlotEvent<? extends InventoryEvent>>> onInteraction;

    SimpleSlot(SlotBuilder<TValue> builder) {
        this.value = builder.value();
        this.valueString = builder.stringValue();
        this.createItem = builder.stackCreate();
        this.slot = builder.index();
    }

    @Override
    public ItemStack createStack() {
        return this.createItem.get();
    }

    @Override
    public Collection<Consumer<SlotClickEvent>> onClick() {
        return Collections.unmodifiableCollection(this.onClick);
    }

    @Override
    public Collection<Consumer<SlotDragEvent>> onDrag() {
        return Collections.unmodifiableCollection(this.onDrag);
    }

    @Override
    public Collection<Consumer<SlotEvent<? extends InventoryEvent>>> onInteract() {
        return Collections.unmodifiableCollection(this.onInteraction);
    }

    @Override
    public OptionalInt preferredPosition() {
        if (slot == null) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(this.slot);
    }

    @Override
    public TValue value() {
        return this.value;
    }

    @Override
    public String valueDisplay() {
        return this.valueString.apply(this.value);
    }
}
