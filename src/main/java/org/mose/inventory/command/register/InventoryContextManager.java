package org.mose.inventory.command.register;

import org.bukkit.entity.Player;
import org.mose.inventory.command.InventoryCommandContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;

public class InventoryContextManager {

    private final Collection<InventoryCommandContext> runningContexts = new LinkedTransferQueue<>();

    public void register(InventoryCommandContext context) {
        this.runningContexts.add(context);
    }

    public void remove(Player player) {
        List<InventoryCommandContext> toRemove = this.runningContexts.stream().filter(c -> c.player().equals(player)).toList();
        this.runningContexts.removeAll(toRemove);
    }

    public Optional<InventoryCommandContext> runningContext(Player player) {
        return this.runningContexts.stream().filter(context -> context.player().equals(player)).findAny();
    }

    public Collection<InventoryCommandContext> runningContexts() {
        return Collections.unmodifiableCollection(this.runningContexts);
    }
}
