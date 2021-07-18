package com.deeplake.backtones.blocks;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.AdvancementUtil;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.MessageDef;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BlockAchvBox extends BaseBlockMJDS {

    Supplier<Item> sellItemSupp;
    String achvName;

    public BlockAchvBox(Supplier<Item> sellItemSupp, String achvName) {
        this.sellItemSupp = sellItemSupp;
        this.achvName = achvName;
    }

    public boolean hasItem()
    {
        return sellItemSupp.get().getItem() != Items.AIR;
    }

    public boolean hasAchv()
    {
        return !achvName.isEmpty();
    }

    public String getStackName()
    {
        return sellItemSupp.get().getDescriptionId();
    }

    public String getAchvName()
    {
        return String.format("%s.advancements.%s.title", IdlFramework.MOD_ID, achvName);
    }

    public ItemStack getAwardStack(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        return new ItemStack(sellItemSupp.get(), 1);
    }

    @Override
    public void animateTick(BlockState p_180655_1_, World world, BlockPos pos, Random random) {
        super.animateTick(p_180655_1_, world, pos, random);
        if (world.isClientSide)
        {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                    pos.getX()+CommonFunctions.flunctate(0.5,1,random),
                    pos.getY()+CommonFunctions.flunctate(0.5,1,random),
                    pos.getZ()+CommonFunctions.flunctate(0.5,1,random),
                    0,0,0
                    );
        }
    }

    //(onBlockActivated)
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide)
        {
            return ActionResultType.SUCCESS;
        }
        else {
            if (AdvancementUtil.hasAdvancement((ServerPlayerEntity) playerEntity, achvName))
            {
                CommonFunctions.SafeSendMsgToPlayer(TextFormatting.YELLOW, playerEntity, MessageDef.BOX_FAIL);
                return ActionResultType.FAIL;
            }
            else {
                AdvancementUtil.giveAdvancement(playerEntity, achvName);
                playerEntity.addItem(getAwardStack(state, world, pos, playerEntity, hand, blockRayTraceResult));
                return ActionResultType.SUCCESS;
            }
        }
    }
}
