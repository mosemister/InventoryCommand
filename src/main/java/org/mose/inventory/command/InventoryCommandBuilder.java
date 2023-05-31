package org.mose.inventory.command;

import org.mose.inventory.command.register.InventoryContextManager;
import org.mose.inventory.command.screen.ScreenSnapshot;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InventoryCommandBuilder {

    private Supplier<InventoryContextManager> manager;
    private Consumer<InventoryCommandContext> onFinish;
    private ScreenSnapshot<?> startingScreen;

    public Supplier<InventoryContextManager> manager() {
        return manager;
    }

    public Consumer<InventoryCommandContext> onFinish() {
        return onFinish;
    }

    public InventoryCommandBuilder setManager(Supplier<InventoryContextManager> manager) {
        this.manager = manager;
        return this;
    }

    public InventoryCommandBuilder setOnFinish(Consumer<InventoryCommandContext> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public InventoryCommandBuilder setStartingScreen(ScreenSnapshot<?> startingScreen) {
        this.startingScreen = startingScreen;
        return this;
    }

    public ScreenSnapshot<?> startingScreen() {
        return startingScreen;
    }
}
