package com.deeplake.backtones.items;

import com.deeplake.backtones.entities.EntityRedArrow;
import com.deeplake.backtones.items.tabs.TabList;
import com.deeplake.backtones.registry.EntityRegistry;
import com.deeplake.backtones.registry.ItemRegistry;
import com.deeplake.backtones.util.IDLNBT;
import com.deeplake.backtones.util.IDLNBTDef;
import com.deeplake.backtones.util.IDLNBTUtil;
import com.deeplake.backtones.util.MJDSDefine;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.function.Predicate;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;
import static net.minecraft.item.BowItem.getPowerForTime;

public class BaseMJDSBow extends ShootableItem implements IVanishable {

    //todo: two types of bow
    public BaseMJDSBow(Properties p_i50040_1_) {
        super(p_i50040_1_.tab(TabList.MISC_GROUP));
    }
    
    public static final Predicate<ItemStack> MJDS_AMMO = (p_220002_0_) -> {
        return p_220002_0_.getItem().is(ItemTags.ARROWS) || p_220002_0_.getItem() == ItemRegistry.QUIVER.get();
    };

    float sound_volume = 1.0f;

    //Massively copied from BowItem
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack stack = playerEntity.getItemInHand(hand);

        final boolean instabuild = playerEntity.abilities.instabuild;
        boolean flag = instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
        ItemStack ammoStack = playerEntity.getProjectile(stack);

        if (net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, world, playerEntity, 999, !ammoStack.isEmpty() || flag) < 0)
            return super.use(world, playerEntity, hand);

        if (!ammoStack.isEmpty() || flag) {
            if (ammoStack.isEmpty()) {
                ammoStack = new ItemStack(Items.ARROW);
            }

            boolean freeOfCost = instabuild || (ammoStack.getItem() instanceof ArrowItem && ((ArrowItem)ammoStack.getItem()).isInfinite(ammoStack, stack, playerEntity));
            if (!world.isClientSide) {
                ArrowItem arrowitem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
                AbstractArrowEntity abstractarrowentity = new EntityRedArrow(EntityRegistry.RED_ARROW.get(), world);
                abstractarrowentity = customArrow(abstractarrowentity);
                abstractarrowentity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot,
                        0.0F, getShootPower(stack, playerEntity), getShootError(stack, playerEntity));

                stack.hurtAndBreak(1, playerEntity, (p_220009_1_) -> p_220009_1_.broadcastBreakEvent(playerEntity.getUsedItemHand()));

                world.addFreshEntity(abstractarrowentity);

                IDLNBTUtil.SetInt(abstractarrowentity, IDLNBTDef.MJDS_ARROW, 1);
            }

            world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                    SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS,
                    sound_volume, getSoundPitch());
            if (!freeOfCost) {
                ammoStack.shrink(1);
                if (ammoStack.isEmpty()) {
                    playerEntity.inventory.removeItem(ammoStack);
                }
            }

            playerEntity.awardStat(Stats.ITEM_USED.get(this));
            activateCooldown(stack, playerEntity);
        }

        return super.use(world, playerEntity, hand);
    }

    public float getShootError(ItemStack stack, PlayerEntity playerEntity)
    {
        return 0.0f;
    }

    public float getShootPower(ItemStack stack, PlayerEntity playerEntity)
    {
        return 1.5f;
    }

    public int LONG_COOLDOWN = 3 * TICK_PER_SECOND;
    public int SHORT_COOLDOWN = (int) (0.2 * TICK_PER_SECOND);

    public float getSoundPitch()
    {
        return 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F;//This is vanilla bow.
    }

    public int getCooldown(ItemStack stack, PlayerEntity playerEntity)
    {
        int maxShoot = getMaxShoot(stack, playerEntity);
        int state = IDLNBTUtil.GetInt(stack, IDLNBTDef.STATE);

        state++;

        if (state >= maxShoot)
        {
            IDLNBTUtil.SetInt(stack, IDLNBTDef.STATE, 0);
            return LONG_COOLDOWN;
        }
        else {
            IDLNBTUtil.SetInt(stack, IDLNBTDef.STATE, state);
            return SHORT_COOLDOWN;
        }
    }

    public int getMaxShoot(ItemStack stack, PlayerEntity playerEntity)
    {
        int ego = IDLNBT.getPlayerIdeallandIntSafe(playerEntity, IDLNBTDef.MJDS_EGO);

        switch (ego)
        {
            case MJDSDefine.POPLON:
                return 2;

            case MJDSDefine.APHRODITE:
                return 3;

            default:
                return 1;
        }
    }


    public void activateCooldown(ItemStack stack, PlayerEntity playerEntity)
    {
        playerEntity.getCooldowns().addCooldown(stack.getItem(), getCooldown(stack, playerEntity));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return MJDS_AMMO;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        arrow.pickup = AbstractArrowEntity.PickupStatus.DISALLOWED;
        arrow.setNoGravity(true);
        arrow.setBaseDamage(8f);
        return arrow;
    }
}
