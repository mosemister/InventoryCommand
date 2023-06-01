package org.mose.inventory.command.screen;

import net.kyori.adventure.text.Component;
import org.mose.inventory.command.slot.Slot;

import java.util.List;
import java.util.Optional;

public interface Screen<TValue> {

    Optional<Component> displayName();

    String identifier();

    List<Slot<TValue>> slots();

}
