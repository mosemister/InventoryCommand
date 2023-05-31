package org.mose.inventory.command.screen.type.chest;

import java.util.Optional;

public enum ChestSizes {

    X1(1),
    X2(2),
    X3(3),
    X4(4),
    X5(5),
    X6(6);

    private final int row;

    ChestSizes(int row) {
        this.row = row;
    }

    public Optional<ChestSizes> nextSize() {
        return this.offset(1);
    }

    private Optional<ChestSizes> offset(int offset) {
        ChestSizes[] sizes = values();
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] == this) {
                if ((i + offset) == (sizes.length)) {
                    return Optional.empty();
                }
                if ((i + offset) < 0) {
                    return Optional.empty();
                }
                return Optional.of(sizes[i + offset]);
            }
        }
        return Optional.empty();
    }

    public Optional<ChestSizes> previousSize() {
        return this.offset(-1);
    }

    public int row() {
        return this.row;
    }

    public int size() {
        return this.row * 9;
    }

    public static ChestSizes bestValue(int capacity) {
        for (ChestSizes chestSizes : values()) {
            if (chestSizes.size() > capacity) {
                return chestSizes;
            }
        }
        throw new IllegalArgumentException("Capacity is too large");
    }
}
