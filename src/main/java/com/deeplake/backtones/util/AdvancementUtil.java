package com.deeplake.backtones.util;

import com.deeplake.backtones.IdlFramework;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class AdvancementUtil {
    public static boolean giveAdvancement(PlayerEntity player, String id)
    {
        if (player.level.isClientSide)
        {
            return false;
        }

        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        Advancement advancement = findAdvancement(player.getServer(), id);
        if (advancement == null)
        {
            IdlFramework.LogWarning("failed to find an advancement:%s", id);
            return false;
        }

        return giveAdvancement(player, advancement);
    }

    public static boolean giveAdvancement(PlayerEntity player, Advancement advancement)
    {
        if (player.level.isClientSide)
        {
            return false;
        }

        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
        if (advancement == null)
        {
            return false;
        }
        AdvancementProgress advancementprogress = playerMP.getAdvancements().getOrStartProgress(advancement);
        if (advancementprogress.isDone()) {
            return false;
        } else {
            for(String s : advancementprogress.getRemainingCriteria()) {
                playerMP.getAdvancements().award(advancement, s);
            }

            return true;
        }
    }

    public static boolean hasAdvancement(ServerPlayerEntity p_192552_2_, Advancement p_192552_3_)
    {
        AdvancementProgress advancementprogress = p_192552_2_.getAdvancements().getOrStartProgress(p_192552_3_);
        return advancementprogress.isDone();
    }

    public static Advancement findAdvancement(MinecraftServer server, String id)
    {
        Advancement advancement = server.getAdvancements().getAdvancement(new ResourceLocation(IdlFramework.MOD_ID, id));
        if (advancement == null)
        {
            advancement = server.getAdvancements().getAdvancement(new ResourceLocation(id));//Also try vanilla
        }

        if (advancement == null)
        {
            IdlFramework.Log("Cannot find advancement:%s", id);
            return null;
        }
        else
        {
            return advancement;
        }
    }
}
