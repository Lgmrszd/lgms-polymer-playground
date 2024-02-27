package com.lgmrszd.lgm_pol_pg.client_tracker;

import com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConfigurationNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.UUID;

import static com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground.LOGGER;
import static com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground.MOD_ID;

public class ClientTracker {

//	public static final Identifier NOTIFY_SERVER = new Identifier(MOD_ID, "notify_server");
    public static final Identifier HELLO = new Identifier(MOD_ID, "hello");
//    public static final int CURRENT_VERSION = 1;

//    private static final WeakHashMap<ServerPlayerEntity, Boolean> hasPlayerClient = new WeakHashMap<>();
    private static final Map<UUID, Boolean> playerHasClient = new Object2BooleanOpenHashMap<>();

    public static final ClientTracker INSTANCE = new ClientTracker();

    public ClientTracker() {
        reset();
    }

    private void reset() {
        playerHasClient.clear();
    }

    private void setPlayerClient(UUID uuid, boolean hasClient) {
        playerHasClient.put(uuid, hasClient);
    }

    private void clearPlayerClient(UUID uuid) {
        playerHasClient.remove(uuid);
    }

    public boolean hasClient(ServerPlayerEntity player) {
        if (playerHasClient.containsKey(player.getUuid())) {
            return playerHasClient.getOrDefault(player.getUuid(), false);
        }
        LOGGER.error("No info on player %s".formatted(player));
        return false;
    }

    private static void onConfigured(ServerConfigurationNetworkHandler handler, MinecraftServer server) {
        boolean needOnClient = !LgmsPolymerPlayground.isPolymerLoaded();
        UUID uuid = handler.getDebugProfile().getId();
        if (ServerConfigurationNetworking.canSend(handler, HELLO)) {
            server.execute(() -> {
                INSTANCE.setPlayerClient(uuid, true);
            });
        } else {
            if (needOnClient) handler.disconnect(Text.literal("Sorry, mod required on client!!\n" +
                    "Alternatively, install Polymer on the server"));
            else {
                server.execute(() -> {
                    INSTANCE.setPlayerClient(uuid, false);
                });
            }
        }
    }

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            INSTANCE.reset();
        });

        ServerConfigurationConnectionEvents.CONFIGURE.register(ClientTracker::onConfigured);

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            UUID uuid = handler.player.getUuid();
            INSTANCE.clearPlayerClient(uuid);
        });

//        ServerConfigurationNetworking.registerGlobalReceiver(NOTIFY_SERVER, (server, handler, buf, responseSender) -> {
//            int clientProtocolVersion = buf.readInt();
//            server.execute(() -> {
//                if (clientProtocolVersion == CURRENT_VERSION) {
//                    handler
//                    ClientTracker.INSTANCE
//                }
//            });
//        });
    }
}
