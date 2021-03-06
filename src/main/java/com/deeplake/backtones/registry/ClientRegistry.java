package com.deeplake.backtones.registry;

import com.deeplake.backtones.entities.model.ModelMajouPlant;
import com.deeplake.backtones.entities.renderer.BatRendererMJDS;
import com.deeplake.backtones.entities.renderer.BushRenderer;
import com.deeplake.backtones.entities.renderer.VoidRenderer;
import net.minecraft.client.renderer.entity.*;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.REVIVE_MIST.get(), VoidRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MJDS_SKELETON.get(), SkeletonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MJDS_SLIME.get(), SlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MJDS_BAT.get(), BatRendererMJDS::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MJDS_WORM.get(), SpiderRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MJDS_BUSH.get(),
                (EntityRendererManager p_i50961_1_)
                        -> new BushRenderer(p_i50961_1_, new ModelMajouPlant(), 1f));


    }
}
