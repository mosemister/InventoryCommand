package org.mose.inventory.command.screen.type.chest;

import org.mose.inventory.command.screen.Screen;

public interface ChestScreen<TValue> extends Screen<TValue> {

    int maxItemsPerPage();
}
