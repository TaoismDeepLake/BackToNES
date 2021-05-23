package com.deeplake.backtones.util;

import com.deeplake.backtones.IdlFramework;
import net.minecraft.block.BlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.util.Random;

import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;

public class CommonFunctions {

//    public static EntityEquipmentSlot slotFromHand(EnumHand hand)
//    {
//        return hand == EnumHand.OFF_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND;
//    }
//
//    public static EnumHand handFromSlot(EntityEquipmentSlot hand)
//    {
//        return hand == EntityEquipmentSlot.OFFHAND ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
//    }

    public static ItemStack copyAndSetCount(@Nullable ItemStack stack, int count)
    {
        if (stack == null || stack.isEmpty())
        {
            return ItemStack.EMPTY;
        }

        ItemStack result = stack.copy();
        result.setCount(count);
        return result;
    }

    public static double flunctate(double ori, double radius, Random random)
    {
        return ori + (random.nextFloat() * 2 - 1) * radius;
    }

    public static boolean isSecondTick(World world)
    {
        return world.getGameTime() % TICK_PER_SECOND == 0;
    }

    static float divider = 52f/9f;
    public static float boomRange(int power)
    {
        return (float) (Math.floor( divider * power ) * 0.3f);
    }

    public static float boomRange(float power)
    {
        return (float) (Math.floor( divider * power ) * 0.3f);
    }
//    public static String writeItemStackToString()
//    {
//        if (stack.isEmpty())
//        {
//            this.writeShort(-1);
//        }
//        else
//        {
//            this.writeShort(Item.getIdFromItem(stack.getItem()));
//            this.writeByte(stack.getCount());
//            this.writeShort(stack.getMetadata());
//            NBTTagCompound nbttagcompound = null;
//
//            if (stack.getItem().isDamageable() || stack.getItem().getShareTag())
//            {
//                nbttagcompound = stack.getItem().getNBTShareTag(stack);
//            }
//
//            this.writeCompoundTag(nbttagcompound);
//        }
//    }

//    public static void teleportToDimension(PlayerEntity player, int dimension, double x, double y, double z)
//    {
//        int oldDimension = player.level.getWorldProvider.getDimension();
//        PlayerEntity PlayerEntity = (PlayerEntity) player;
//        MinecraftServer server = player.level.getMinecraftServer();
//        WorldServer worldServer = server.getWorld(dimension);
//        player.addExperienceLevel(0);
//
//        if (worldServer == null || worldServer.getMinecraftServer() == null)
//        {
//            throw new IllegalArgumentException("Dimension: "+dimension+" doesn't exist!");
//        }
//
//        //todo
//        //worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(PlayerEntity, dimension, new CustomTeleporter(worldServer, x, y, z));
//        player.setPositionAndUpdate(x, y, z);
//    }

    public static float GetTemperatureHere(Entity creature)
    {
        BlockPos pos = creature.blockPosition();
        World world = creature.level;
        Biome biome = world.getBiome(pos);
        return biome.getTemperature(pos);
    }

    public static float GetTemperatureHere(BlockPos pos, World world)
    {
        Biome biome = world.getBiome(pos);
        return biome.getTemperature(pos);
    }

    public static int SecondToTicks(int ticks) {
        return ticks * TICK_PER_SECOND;
    }

    public static int SecondToTicks(float ticks) {
        return (int)(ticks * TICK_PER_SECOND);
    }

//    public static void BroadCastByKey(String key, Object... args) {
//        FMLCommonLaunchHandler.instance().getMinecraftServerInstance().getPlayerList().sendMessage(new TranslationTextComponent(key, args));
//    }

    public static void SafeSendMsgToPlayer(LivingEntity player, String key, Object... args)
    {
        //Please note that you can only put %s as arguments. If you put %d, it's not going to translate.
        if ((!player.level.isClientSide) && player instanceof PlayerEntity)
        {
            player.sendMessage((new TranslationTextComponent(key, args)), Util.NIL_UUID);
        }
    }

    public static void SafeSendMsgToPlayer(TextFormatting style, LivingEntity player, String key, Object... args)
    {
        //Please note that you can only put %s as arguments. If you put %d, it's not going to translate.
        if (player instanceof PlayerEntity)
        {
            TranslationTextComponent translationTextComponent = new TranslationTextComponent(key, args);
            translationTextComponent.withStyle(style);
            player.sendMessage(translationTextComponent, Util.NIL_UUID);
        }
    }

    public static void SendMsgToPlayer(PlayerEntity playerMP, String key)
    {
        playerMP.sendMessage(new TranslationTextComponent(key), Util.NIL_UUID);
    }

    public static void SendMsgToPlayerStyled(PlayerEntity playerMP, String key, TextFormatting style, Object... args)
    {
        TranslationTextComponent TranslationTextComponent = new TranslationTextComponent(key, args);
        TranslationTextComponent.withStyle(style);
        playerMP.sendMessage(TranslationTextComponent, Util.NIL_UUID);
    }


    public static void SendMsgToPlayer(PlayerEntity playerMP, String key, Object... args)
    {
        playerMP.sendMessage((new TranslationTextComponent(key, args)), Util.NIL_UUID);
    }

    @OnlyIn(Dist.CLIENT)
    public static String GetStringLocalTranslated(String key) {
        //return "WIP";
        return I18n.get(key);

    }

    public static void FillWithBlockCornered(World world, BlockPos origin, int lengthX, int lengthY, int lengthZ, BlockState newState) {
        BlockPos target;
        for(int x = 0;
            x < lengthX;x++) {
            for (int y = 0; y < lengthY; y++) {
                for (int z = 0; z < lengthZ; z++) {
                    target = origin.offset(x, y, z);
                    //BlockState targetBlock = world.getBlockState(target);
                    world.setBlock(target, newState, 0);
                }
            }
        }
    }

    public static void FillWithBlockCentered(World world, BlockPos origin, int rangeX, int rangeY, int rangeZ, BlockState newState) {
        BlockPos target;
        for(int x = -rangeX;
        x <=rangeX;x++) {
            for (int y = -rangeY; y <= rangeY; y++) {
                for (int z = -rangeZ; z <= rangeZ; z++) {
                    target = origin.offset(x, y, z);
                    //BlockState targetBlock = world.getBlockState(target);
                    world.setBlock(target, newState, 0);
                }
            }
        }
    }

    public static void BuildWallWithBlockCentered(World world, BlockPos origin, int rangeX, int height, int rangeZ, BlockState newState) {
        BlockPos target;
        for(int x = -rangeX;
            x <=rangeX;x++) {
            for (int y = 0; y < height; y++) {
                for (int z = -rangeZ; z <= rangeZ; z++) {
                    target = origin.offset(x, y, z);
                    //BlockState targetBlock = world.getBlockState(target);
                     world.setBlock(target, newState,0);
                }
            }
        }
    }

    public static void LogPlayerAction(LivingEntity living, String action){
        IdlFramework.Log(String.format("%s(%s): %s",living.getName(), living.getStringUUID(), action));
    }

    public static boolean repairItem(ItemStack stack, int amount)
    {
        if (!stack.isDamageableItem())
        {
            return false;
        }
        else {
            int newVal = stack.getDamageValue() - amount;
            //if (newVal < 0) newVal = 0;
            stack.setDamageValue(newVal);
            return true;
        }
    }

    public static void CopyNormalAttr(LivingEntity ori, LivingEntity to)
    {
        CopyAttr(ori, to, Attributes.FOLLOW_RANGE);
        CopyAttr(ori, to, Attributes.MAX_HEALTH);
        CopyAttr(ori, to, Attributes.MOVEMENT_SPEED);
    }


    public static void CopyAttr(LivingEntity ori, LivingEntity to, Attribute attrType)
    {
        to.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(ori.getAttribute(attrType).getValue());
    }

    public static int XPForLevel(int lv)
    {
        /**
         * This method returns the cap amount of experience that the experience bar can hold. With each level, the
         * experience cap on the player's experience bar is raised by 10.
         */

            if (lv >= 30)
            {
                return 112 + (lv - 30) * 9;
            }
            else
            {
                return lv >= 15 ? 37 + (lv - 15) * 5 : 7 + lv * 2;
            }

    }


//    public static boolean TryConsumePlayerXP(PlayerEntity player, int XP)
//    {
//        //float curXP = player.experience;
////        float curLv = player.experienceLevel;
////        float costLeft = XP;
////
////        int playerTotalXP = 0;
////        for (int i = 1; i <= curLv; i++)
////        {
////            playerTotalXP += XPForLevel(i);
////        }
////        playerTotalXP += player.ex;
////
////        if (playerTotalXP < XP)
////        {
////            //not enough
////            return false;
////        }
////
////        //todo not working here
////        while (costLeft > 0 && (player.experienceLevel > 0 || player.experience > 0))
////        {
////            if (player.experience > costLeft)
////            {
////                player.experience -= costLeft;
////                costLeft = 0;
////                Idealland.Log("A");
////            }
////            else {
////                costLeft -= player.experience;
////                Idealland.Log("B");
////                if (player.experienceLevel > 0)
////                {
////                    player.experienceLevel--;
////                    player.experience = XPForLevel(player.experienceLevel);
////                    Idealland.Log(String.format("player.experience = %d", XPForLevel(player.experienceLevel)));
////                }
////            }
////        }
////        Idealland.Log(String.format("Lv= %s, xp = %s", player.experienceLevel, player.experience));
////        return true;
//    }

    public static boolean isItemRangedWeapon(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return false;
        }

        Item item = stack.getItem();
        if (item instanceof BowItem)
        {
            return true;
        }

//        if (item instanceof BaseItemIDF)
//        {
//            return ((BaseItemIDF) item).isRangedWeaponItem();
//        }
        return false;
    }

//    public static void WriteGraveToSign(PlayerEntity player, World world, TileEntity tileEntity1) {
//        if (tileEntity1 instanceof SignTileEntity)
//        {
//            WriteGraveToSign(player.getDisplayName(), world, tileEntity1);
//        }
//    }

//    private static void WriteGraveToSign(ITextComponent name, World world, TileEntity tileEntity1) {
//        if (tileEntity1 instanceof SignTileEntity)
//        {
//            ((SignTileEntity) tileEntity1).[0] = name;
//            ((SignTileEntity) tileEntity1).signText[1] = new TextComponentString("R.I.P.");
//
//            Calendar calendar = world.getCurrentDate();
//            Idealland.Log("calendar:", calendar);
//            ((SignTileEntity) tileEntity1).signText[2] = new TextComponentString(
//                    CommonDef.formatDate.format(calendar.getTime())
//            );
//
//            ((SignTileEntity) tileEntity1).signText[3] = new TextComponentString(
//                    CommonDef.formatTime.format(calendar.getTime())
//            );
//        }
//    }

//    @OnlyIn(Dist.CLIENT)
//    public static boolean isShiftPressed()
//    {
//        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
//    }

    public static void sendBasicMsg(ItemStack stack, PlayerEntity player, int index)
    {
        if (player.level.isClientSide)
        {
            return;
        }

        SendMsgToPlayer((PlayerEntity) player, String.format("%s.msg.%d", stack.getDescriptionId(), index));
    }

    public static void addToEventBus(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
    }

    public static ResourceLocation getResLoc(String key)
    {
        return new ResourceLocation(IdlFramework.MOD_ID, key);
    }
}
