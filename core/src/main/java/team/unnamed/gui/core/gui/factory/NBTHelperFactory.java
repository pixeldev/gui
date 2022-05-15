package team.unnamed.gui.core.gui.factory;

import team.unnamed.gui.abstraction.item.nbt.NBTHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static team.unnamed.gui.core.version.ServerVersionProvider.SERVER_VERSION;

public final class NBTHelperFactory {

    private static final Constructor<?> NBT_CONSTRUCTOR;

    static {
        try {
            NBT_CONSTRUCTOR = Class.forName(
                    "team.unnamed.gui.v" + SERVER_VERSION + ".NBTHelper" + SERVER_VERSION
            ).getConstructor();
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Your server version isn't supported by Unnamed GUI yet!");
        }

    }

    public static NBTHelper create() {
        try {
            return (NBTHelper) NBT_CONSTRUCTOR.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(
                    "Unable to create an instance for the NBTHelper class", e
            );
        }
    }
}