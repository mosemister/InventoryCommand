package org.mose.inventory.command.screen.common;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.mose.inventory.command.screen.ScreenBuilder;
import org.mose.inventory.command.screen.type.chest.ChestScreenBuilder;
import org.mose.inventory.command.slot.Slot;
import org.mose.inventory.command.slot.SlotBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonScreens {

    public static ChestScreenBuilder<Player> allPlayers(BiFunction<SlotBuilder<Player>, Player, Slot<Player>> additional) {
        return allPlayers(() -> new ArrayList<Player>(Bukkit.getOnlinePlayers()).stream(), additional);
    }

    private static <TPlayer extends OfflinePlayer> ChestScreenBuilder<TPlayer> allPlayers(Supplier<Stream<TPlayer>> players,
                                                                                          BiFunction<SlotBuilder<TPlayer>, TPlayer, Slot<TPlayer>> additional) {
        return ScreenBuilder
                .<TPlayer>chest()
                .setIdentifier("players")
                .setMaxSlotsPerPage(5)
                .setSlots(() -> players
                        .get()
                        .filter(play -> play.getName() != null)
                        .sorted(Comparator.comparing(OfflinePlayer::getName))
                        .map(player -> additional.apply(
                                new SlotBuilder<TPlayer>().setValue(player).setStringValue(OfflinePlayer::getName).setPlayerHead(player).setReadOnly(), player))
                        .collect(Collectors.toList()));
    }

    public static ChestScreenBuilder<OfflinePlayer> allUsers(BiFunction<SlotBuilder<OfflinePlayer>, OfflinePlayer, Slot<OfflinePlayer>> additional) {
        return allPlayers(() -> Arrays.stream(Bukkit.getOfflinePlayers()), additional);
    }
}
