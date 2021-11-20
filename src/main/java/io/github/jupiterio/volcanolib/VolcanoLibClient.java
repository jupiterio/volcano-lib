package io.github.jupiterio.volcanolib;

import net.fabricmc.api.ClientModInitializer;

public class VolcanoLibClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VolcanoLib.log("Initializing Client");
        VolcanoLib.isClient = true;
    }
}
