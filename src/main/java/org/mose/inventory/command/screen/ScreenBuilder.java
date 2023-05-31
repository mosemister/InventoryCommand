package org.mose.inventory.command.screen;

import org.mose.inventory.command.screen.type.chest.ChestScreenBuilder;

public class ScreenBuilder {

    public static <TValue> ChestScreenBuilder<TValue> chest() {
        return new ChestScreenBuilder<>();
    }


}
