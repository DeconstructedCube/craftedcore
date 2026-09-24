package dev.tocraft.craftedcore.registration.fabric;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import dev.tocraft.craftedcore.data.SynchronizedJsonReloadListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SuppressWarnings("unused")
@ApiStatus.Internal
public class SynchronizedReloadListenerRegistryImpl {
    public static void onRegister(SynchronizedJsonReloadListener reloadListener, Identifier id) {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new IdentifiableResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return id;
            }

            @Override
            public @NotNull String getName() {
                return reloadListener.getName();
            }

            @Override
            public @NotNull CompletableFuture<Void> reload(PreparableReloadListener.SharedState sharedState, Executor executor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor executor2) {
                return reloadListener.reload(sharedState, executor, preparationBarrier, executor2);
            }
        });
    }
}
