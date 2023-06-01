package org.mose.example;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.mose.inventory.command.register.InventoryContextManager;
import org.mose.inventory.command.register.InventoryListener;

public final class InventoryExample extends JavaPlugin {

    private static InventoryExample plugin;
    private InventoryContextManager manager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendPlainMessage("Player only command");
            return false;
        }
        PlayerDisplayNameInventoryCommand.COMMAND.start((Player) sender);
        return true;
    }

    public InventoryContextManager manager(){
        return this.manager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        this.manager = new InventoryContextManager();
        Bukkit.getPluginManager().registerEvents(new InventoryListener(() -> this.manager), this);
    }

    public static InventoryExample plugin() {
        return plugin;
    }
}
