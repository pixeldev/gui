package team.unnamed.gui.core.gui.factory;

import team.unnamed.gui.abstraction.item.nbt.ItemStackNBT;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION;

public final class ItemStackNBTFactory {

    private static final Constructor<?> NBT_CONSTRUCTOR;

    static {
        try {
            NBT_CONSTRUCTOR = Class.forName(
                    "team.unnamed.gui.v" + SERVER_VERSION + ".ItemStackNBT" + SERVER_VERSION
            ).getConstructor();
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Your server version isn't supported by Unnamed GUI yet!");
        }

    }

    public static ItemStackNBT getInstance() {
        try {
            return (ItemStackNBT) NBT_CONSTRUCTOR.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(
                    "Unable to create an instance for the ItemStackNBT class", e
            );
        }
    }
}