package org.mose.example;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.mose.inventory.command.InventoryCommand;
import org.mose.inventory.command.InventoryCommandBuilder;
import org.mose.inventory.command.screen.ScreenSnapshot;
import org.mose.inventory.command.screen.common.CommonScreens;
import org.mose.inventory.command.slot.events.SlotEvent;

public class PlayerDisplayNameInventoryCommand {

    public static final ScreenSnapshot<Player> PLAYER_SCREEN = CommonScreens
            .allPlayers((slot, player) -> slot.addOnClick(SlotEvent::complete).build())
            .setDisplayName(Component.text("players").color(TextColor.color(100, 100, 255)))
            .build();

    public static final InventoryCommand COMMAND = new InventoryCommandBuilder().setStartingScreen(PLAYER_SCREEN).setOnFinish((context) -> {
        Player selected = context.input(PLAYER_SCREEN);
        context.player().sendMessage(Component.text("players name: ").append(selected.displayName()));
    }).setManager(() -> InventoryExample.plugin().manager()).build();

}
