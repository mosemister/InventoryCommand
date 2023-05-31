package org.mose.inventory.command.screen;

import net.kyori.adventure.text.Component;
import org.mose.inventory.command.slot.Slot;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractScreenBuilder<TValue, TBuilder extends AbstractScreenBuilder<TValue, TBuilder>> {

    private Component displayName;
    private String identifier;
    private Supplier<List<Slot<TValue>>> slots;

    public abstract ScreenSnapshot<TValue> build();

    public Component displayName() {
        return this.displayName;
    }

    public String identifier() {
        return this.identifier;
    }

    public TBuilder setDisplayName(Component displayName) {
        this.displayName = displayName;
        return (TBuilder) this;
    }

    public TBuilder setIdentifier(String identifier) {
        this.identifier = identifier;
        return (TBuilder) this;
    }

    public TBuilder setSlots(Supplier<List<Slot<TValue>>> slots) {
        this.slots = slots;
        return (TBuilder) this;
    }

    public Supplier<List<Slot<TValue>>> slots() {
        return this.slots;
    }
}
