package com.lgmrszd.lgm_pol_pg.client_tracker;

import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;

import static com.lgmrszd.lgm_pol_pg.client_tracker.ClientTracker.*;
import static com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground.LOGGER;

public class ClientTrackerClient {
    public static void init() {
        ClientConfigurationNetworking.registerGlobalReceiver(HELLO, (client, handler, buf, responseSender) -> {
            LOGGER.info("Server has the mod!");
        });
    }
}
