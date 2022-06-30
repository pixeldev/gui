package team.unnamed.gui.menu.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Slots {

    private Slots() {
        // the class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

    public static List<Integer> getBorderSlots(int rows) {
        if (rows < 3) {
            return Collections.emptyList();
        }

        List<Integer> slots = new ArrayList<>();
        int totalSlots = rows * 9;
        slots.addAll(getRowSlots(1));
        slots.addAll(getRowSlots(rows));
        slots.addAll(getColumnSlots(1, totalSlots));
        slots.addAll(getColumnSlots(9, totalSlots));

        return slots;
    }

    public static List<Integer> getRowSlots(int row) {
        List<Integer> slots = new ArrayList<>();
        int indexStart = row == 1 ? 0 : (row - 1) * 9;

        for (int slot = indexStart; slot < indexStart + 9; slot++) {
            slots.add(slot);
        }

        return slots;
    }

    public static List<Integer> getColumnSlots(int column, int slots) {
        List<Integer> columnSlots = new ArrayList<>();
        int indexStart = column - 1;
        int indexEnd = (slots - 9) + column;

        for (int slot = indexStart; slot <= indexEnd; slot += 9) {
            columnSlots.add(slot);
        }

        return columnSlots;
    }

}
