package org.mose.inventory.command.screen.type.chest;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.mose.inventory.command.screen.CurrentScreen;
import org.mose.inventory.command.screen.ScreenSnapshot;
import org.mose.inventory.command.slot.Slot;

import java.util.List;
import java.util.Optional;

public class CurrentChestScreen<TValue> implements ChestScreen<TValue>, CurrentScreen<TValue> {

    private final ChestScreenSnapshot<TValue> basedOn;
    private InventoryView inventory;
    private int page;

    public CurrentChestScreen(ChestScreenSnapshot<TValue> snapshot, Player player) {
        this.basedOn = snapshot;
        this.setPage(1, player);
    }

    @Override
    public ScreenSnapshot<TValue> basedOn() {
        return this.basedOn;
    }

    @Override
    public InventoryView bukkitInventory() {
        return this.inventory;
    }

    @Override
    public Slot<TValue> slot(int slotIndex, ItemStack itemInSlot) {
        @SuppressWarnings("OptionalGetWithoutIsPresent") Optional<Slot<TValue>> indexMatch = this
                .slots()
                .stream()
                .filter(slot -> slot.preferredPosition().isPresent())
                .filter(slot -> slot.preferredPosition().getAsInt() == slotIndex)
                .findAny();
        return indexMatch.orElseGet(() -> this
                .slots()
                .stream()
                .filter(slot -> slot.createStack().equals(itemInSlot))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Provided information does not belong to this inventory")));
    }

    @Override
    public Optional<Component> displayName() {
        if (this.inventory == null) {
            return this.basedOn.displayName();
        }
        return Optional.of(this.inventory.title());
    }

    @Override
    public String identifier() {
        return this.basedOn.identifier();
    }

    @Override
    public List<Slot<TValue>> slots() {
        return this.basedOn.slots();
    }

    public boolean isPaged() {
        return this.slots().size() > this.maxItemsPerPage();
    }

    @Override
    public int maxItemsPerPage() {
        return this.basedOn.maxItemsPerPage();
    }

    public int page() {
        return this.page;
    }

    void setPage(int page, Player player) {
        List<Slot<TValue>> slots = this.slotsForPage(page);
        boolean isPaging = this.isPaged();
        ChestSizes size = ChestSizes.bestValue(slots.size());

        Inventory bukkitInv;
        if (this.displayName().isPresent()) {
            bukkitInv = Bukkit.createInventory(player, size.size(), this.displayName().get());
        } else {
            bukkitInv = Bukkit.createInventory(player, size.size());
        }
        final Inventory finalBukkitInv = bukkitInv;

        //noinspection OptionalGetWithoutIsPresent
        slots
                .stream()
                .filter(slot -> slot.preferredPosition().isPresent())
                .forEach(slot -> finalBukkitInv.setItem(slot.preferredPosition().getAsInt(), slot.createStack()));
        slots.stream().filter(slot -> slot.preferredPosition().isEmpty()).forEach(slot -> finalBukkitInv.addItem(slot.createStack()));

        //TODO -> add paging slots

        this.inventory = player.openInventory(bukkitInv);
        this.page = page;
    }

    public void setPage(int page) {
        this.setPage(page, (Player) this.inventory.getPlayer());
    }

    public List<Slot<TValue>> slotsForPage(int page) {
        int totalNumberOfSlots = this.slots().size();
        int pageSize = this.maxItemsPerPage();
        if (this.isPaged()) {
            pageSize -= 9;
        }
        int lower = Math.min(pageSize, totalNumberOfSlots) * (page - 1);
        int higher = lower + pageSize;
        if (higher > totalNumberOfSlots) {
            higher = totalNumberOfSlots;
        }
        return this.slots().subList(lower, higher);
    }
}
