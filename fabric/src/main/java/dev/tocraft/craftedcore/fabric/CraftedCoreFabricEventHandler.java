package dev.tocraft.craftedcore.fabric;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import org.jetbrains.annotations.ApiStatus;
import dev.tocraft.craftedcore.event.common.CommandEvents;
import dev.tocraft.craftedcore.event.common.PlayerEvents;
import dev.tocraft.craftedcore.event.common.ServerLevelEvents;

@ApiStatus.Internal
public class CraftedCoreFabricEventHandler {

    public static void initialize() {
        EntitySleepEvents.ALLOW_SLEEPING.register((player, sleepingPos) -> {
            net.minecraft.world.InteractionResult res = PlayerEvents.ALLOW_SLEEP_TIME.invoke().allowSleepTime(player, sleepingPos, true);
            return res == net.minecraft.world.InteractionResult.FAIL ? net.minecraft.world.entity.player.Player.BedSleepingProblem.OTHER_PROBLEM : null;
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CommandEvents.REGISTRATION.invoke().register(dispatcher, registryAccess, environment));
        ServerWorldEvents.LOAD.register((server, world) -> ServerLevelEvents.LEVEL_LOAD.invoke().call(world));
        ServerWorldEvents.UNLOAD.register((server, world) -> ServerLevelEvents.LEVEL_UNLOAD.invoke().call(world));
    }
}
