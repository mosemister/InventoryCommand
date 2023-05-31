package org.mose.inventory.command.slot;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.mose.inventory.command.slot.events.SlotClickEvent;
import org.mose.inventory.command.slot.events.SlotDragEvent;
import org.mose.inventory.command.slot.events.SlotEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SlotBuilder<TValue> {

    private Integer index;
    private final Collection<Consumer<SlotClickEvent>> onClick = new HashSet<>();
    private final Collection<Consumer<SlotDragEvent>> onDrag = new HashSet<>();
    private final Collection<Consumer<SlotEvent<? extends InventoryEvent>>> onInteraction = new HashSet<>();
    private Supplier<ItemStack> stackCreate;
    private Function<TValue, String> stringValue = Object::toString;
    private TValue value;

    public SlotBuilder<TValue> addOnClick(Consumer<SlotClickEvent> onClick) {
        this.onClick.add(onClick);
        return this;
    }

    public SlotBuilder<TValue> addOnDrag(Consumer<SlotDragEvent> onDrag) {
        this.onDrag.add(onDrag);
        return this;
    }

    public SlotBuilder<TValue> addOnInteraction(Consumer<SlotEvent<? extends InventoryEvent>> onInteraction) {
        this.onInteraction.add(onInteraction);
        return this;
    }

    public Slot<TValue> build() {
        return new SimpleSlot<>(this);
    }

    public Integer index() {
        return this.index;
    }

    public Collection<Consumer<SlotClickEvent>> onClick() {
        return this.onClick;
    }

    public Collection<Consumer<SlotDragEvent>> onDrag() {
        return this.onDrag;
    }

    public Collection<Consumer<SlotEvent<? extends InventoryEvent>>> onInteraction() {
        return this.onInteraction;
    }

    public SlotBuilder<TValue> setCreateStack(Supplier<ItemStack> stackCreate) {
        this.stackCreate = stackCreate;
        return this;
    }

    public SlotBuilder<TValue> setIndex(Integer index) {
        this.index = index;
        return this;
    }

    public SlotBuilder<TValue> setPlayerHead(OfflinePlayer player) {
        return this.setPlayerHead(player, stack -> stack);
    }

    public SlotBuilder<TValue> setPlayerHead(OfflinePlayer player, Function<ItemStack, ItemStack> additional) {
        return this.setCreateStack(() -> {
            ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
            stack.editMeta(meta -> {
                Component displayName = Component.text(Objects.requireNonNull(player.getName()));
                Player online = player.getPlayer();
                if (null != online) {
                    displayName = online.displayName();
                }
                meta.displayName(displayName);
            });
            stack.editMeta(SkullMeta.class, meta -> meta.setOwningPlayer(player));
            return additional.apply(stack);
        });
    }

    public SlotBuilder<TValue> setReadOnly() {
        return this.addOnInteraction(event -> {
            if (event.bukkitEvent() instanceof Cancellable cancellable) {
                cancellable.setCancelled(true);
            }
        });
    }

    public SlotBuilder<TValue> setStringValue(Function<TValue, String> stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public SlotBuilder<TValue> setValue(TValue value) {
        this.value = value;
        return this;
    }

    public Supplier<ItemStack> stackCreate() {
        return this.stackCreate;
    }

    public Function<TValue, String> stringValue() {
        return this.stringValue;
    }

    public TValue value() {
        return this.value;
    }
}
