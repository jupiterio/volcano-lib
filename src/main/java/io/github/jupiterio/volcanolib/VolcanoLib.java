package io.github.jupiterio.volcanolib;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.item.FoodComponents;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VolcanoLib implements ModInitializer {
    public static final String MOD_ID = "volcano-lib";
    public static final String MOD_NAME = "Volcano Lib";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static boolean isClient = false;

    @Override
    public void onInitialize() {
        log("Initializing");
    }

    public static void log(String message){
        LOGGER.info(message);
    }

    public static void warn(String message){
        LOGGER.warn(message);
    }

    public static void warn(String message, Throwable t){
        LOGGER.warn(message, t);
    }

    public static void error(String message, Throwable t){
        LOGGER.error(message, t);
    }

    public static void debug(String message){
        LOGGER.debug(message);
    }

    public static void debug(String message, Throwable t){
        LOGGER.debug(message, t);
    }
}
