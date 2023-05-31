package org.mose.inventory.command.screen.type.chest;

import org.mose.inventory.command.screen.AbstractScreenBuilder;

public class ChestScreenBuilder<TValue> extends AbstractScreenBuilder<TValue, ChestScreenBuilder<TValue>> {

    private int maxSlotsPerPage;

    @Override
    public ChestScreenSnapshot<TValue> build() {
        return new ChestScreenSnapshot<>(this);
    }

    public int maxSlotsPerPage() {
        return this.maxSlotsPerPage;
    }

    public ChestScreenBuilder<TValue> setMaxRowsPerPage(ChestSizes rows) {
        this.maxSlotsPerPage = rows.size();
        return this;
    }

    public ChestScreenBuilder<TValue> setMaxSlotsPerPage(int maxSlotsPerPage) {
        if (maxSlotsPerPage > ChestSizes.X6.size()) {
            throw new IllegalArgumentException("Maximum slot count is " + ChestSizes.X6.size() + " in a double chest");
        }
        this.maxSlotsPerPage = maxSlotsPerPage;
        return this;
    }
}
