package org.mose.inventory.command.screen;

import net.kyori.adventure.text.Component;
import org.mose.inventory.command.slot.Slot;

import java.util.List;

public interface Screen<TValue> {

    Component displayName();

    String identifier();

    List<Slot<TValue>> slots();

}
