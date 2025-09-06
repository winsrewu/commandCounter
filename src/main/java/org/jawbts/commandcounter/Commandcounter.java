package org.jawbts.commandcounter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class Commandcounter implements ModInitializer {
    private static boolean isEnabled = false;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(this::onTick);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("commandcounter").executes(context -> {
                isEnabled =!isEnabled;
                return 1;
            }));
        });
    }

    private void onTick(MinecraftServer minecraftServer) {
        if (!isEnabled) {
            return;
        }

        long k = Counter.getInstance().tick();

        minecraftServer.getPlayerManager().getPlayerList().forEach(player -> player.sendMessage(Text.of("Commands executed: " + k)));
    }
}
