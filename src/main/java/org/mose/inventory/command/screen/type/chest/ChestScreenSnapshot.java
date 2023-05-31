package org.mose.inventory.command.screen.type.chest;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.mose.inventory.command.screen.CurrentScreen;
import org.mose.inventory.command.screen.ScreenSnapshot;
import org.mose.inventory.command.slot.Slot;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ChestScreenSnapshot<TValue> implements ScreenSnapshot<TValue>, ChestScreen<TValue> {

    private final String identifier;
    private final Component displayName;
    private final Supplier<List<Slot<TValue>>> slots;
    private final int maxItemsPerPage;

    public ChestScreenSnapshot(ChestScreenBuilder<TValue> builder) {
        this.identifier = builder.identifier();
        this.displayName = builder.displayName();
        this.maxItemsPerPage = builder.maxSlotsPerPage();
        this.slots = builder.slots();
    }

    @Override
    public CurrentScreen<TValue> display(Player player) {
        return new CurrentChestScreen<>(this, player);
    }

    @Override
    public Component displayName() {
        return this.displayName;
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public List<Slot<TValue>> slots() {
        return Collections.unmodifiableList(this.slots.get());
    }

    @Override
    public int maxItemsPerPage() {
        return this.maxItemsPerPage;
    }
}
