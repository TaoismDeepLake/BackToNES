package com.deeplake.backtones.registry;

import com.deeplake.backtones.entities.renderer.VoidRenderer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistry {
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RED_ARROW.get(), TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CERA_ARROW.get(), TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DAMP_SPHERE.get(), VoidRenderer::new);
    }
}
