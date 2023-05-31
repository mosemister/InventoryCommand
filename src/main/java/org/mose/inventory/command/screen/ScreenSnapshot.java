package org.mose.inventory.command.screen;

import org.bukkit.entity.Player;

public interface ScreenSnapshot<TValue> extends Screen<TValue> {

    CurrentScreen<TValue> display(Player player);
}
