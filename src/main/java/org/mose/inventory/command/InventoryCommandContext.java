package org.mose.inventory.command;

import org.bukkit.entity.Player;
import org.mose.inventory.command.screen.CurrentScreen;
import org.mose.inventory.command.screen.Screen;
import org.mose.inventory.command.screen.ScreenSnapshot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InventoryCommandContext {

    private final Map<String, Object> currentInput = new HashMap<>();
    private final Player targetPlayer;
    private final InventoryCommand command;
    private CurrentScreen<?> currentScreen;
    private boolean didFinishCorrectly;

    InventoryCommandContext(Player player, CurrentScreen<?> currentScreen, InventoryCommand command) {
        this.targetPlayer = player;
        this.currentScreen = currentScreen;
        this.command = command;
    }

    private void addInput(Object value) {
        String identifier = this.currentScreen.identifier();
        this.currentInput.put(identifier, value);
    }

    public InventoryCommand command() {
        return this.command;
    }

    public void complete(Object finishValue) {
        this.didFinishCorrectly = true;
        this.addInput(finishValue);
        this.forceComplete();
    }

    public CurrentScreen<?> currentScreen() {
        return this.currentScreen;
    }

    public boolean didCompleteCorrectly() {
        return this.didFinishCorrectly;
    }

    public void forceComplete() {
        this.player().closeInventory();
        this.command().finish(this);
        this.command.manager().remove(this.targetPlayer);
    }

    public Map<String, Object> input() {
        return Collections.unmodifiableMap(this.currentInput);
    }

    @SuppressWarnings("unchecked")
    public <T> T input(String inventoryIdentifier) {
        return (T) this.currentInput.get(inventoryIdentifier);
    }

    public <T> T input(Screen<T> screen) {
        return this.input(screen.identifier());
    }

    public void nextScreen(Object finishValue, ScreenSnapshot<?> newScreen) {
        this.addInput(finishValue);
        this.currentScreen = newScreen.display(this.targetPlayer);
    }

    public Player player() {
        return this.targetPlayer;
    }

}
