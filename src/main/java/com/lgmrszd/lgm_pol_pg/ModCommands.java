package com.lgmrszd.lgm_pol_pg;

import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTracker;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


import static net.minecraft.server.command.CommandManager.*;
public class ModCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register(ModCommands::registerCommands);
    }

    private static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, RegistrationEnvironment environment) {
        dispatcher.register(literal("lgpg")
                .executes(context -> {
                    context.getSource().getWorld().getPlayers().forEach(player -> {
                        boolean hasClient = ClientTracker.INSTANCE.hasClient(player);
                        context.getSource().sendMessage(
                                player.getDisplayName().copy()
                                        .append(" ")
                                        .append(
                                                hasClient ?
                                                        Text.literal("has mod on client").formatted(Formatting.GREEN) :
                                                        Text.literal("missing mod on client").formatted(Formatting.RED)
                                        )
                        );
                    });
                    return 1;
                })
        );
    }
}
