package org.jawbts.commandcounter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

public class Commandcounter implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(this::onTick);
    }

    private void onTick(MinecraftServer minecraftServer) {
        long k = Counter.getInstance().tick();

        System.out.println("Commands executed: " + k);

        minecraftServer.getPlayerManager().getPlayerList().forEach(player -> player.sendMessage(Text.of("Commands executed: " + k)));
    }
}
