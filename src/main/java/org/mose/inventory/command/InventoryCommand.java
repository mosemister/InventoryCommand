package org.mose.inventory.command;

import org.bukkit.entity.Player;
import org.mose.inventory.command.register.InventoryContextManager;
import org.mose.inventory.command.screen.CurrentScreen;
import org.mose.inventory.command.screen.ScreenSnapshot;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InventoryCommand {

    private final ScreenSnapshot<?> startingScreen;
    private final Consumer<InventoryCommandContext> onFinish;
    private final Supplier<InventoryContextManager> manager;

    InventoryCommand(InventoryCommandBuilder builder) {
        this.manager = builder.manager();
        this.onFinish = builder.onFinish();
        this.startingScreen = builder.startingScreen();
    }

    void finish(InventoryCommandContext context) {
        this.onFinish.accept(context);
    }

    public InventoryContextManager manager() {
        return this.manager.get();
    }

    public InventoryCommandContext start(Player player) {
        CurrentScreen<?> current = this.startingScreen.display(player);
        InventoryCommandContext context = new InventoryCommandContext(player, current, this);
        manager.get().register(context);
        return context;
    }

}
