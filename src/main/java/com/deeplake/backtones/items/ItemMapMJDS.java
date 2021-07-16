package com.deeplake.backtones.items;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.EgoUtil;
import com.deeplake.backtones.util.IDLNBTDef;
import com.deeplake.backtones.util.MJDSDefine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.deeplake.backtones.util.CommonDef.NEWLINE;
import static com.deeplake.backtones.util.CommonDef.TICK_PER_SECOND;
import static com.deeplake.backtones.util.IDLNBTDef.ORI_POS;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class ItemMapMJDS extends BaseItemIDF implements INeedLogNBT{

    public static final int BLANK = 0;
    public static final int PASS_ = 1;
    public static final int BOSS_ = 2;

    public static final int[][] CASTLE_MAP =
            {
                    {0,0,0,0,-4,1,1, 0,0,0,0},
                    {0,0,0,1, 1,1,1, 1,0,0,0},
                    {0,0,0,1, 1,0,1, 1,0,0,0},

                   {-2,0,1,0, 1,1,1,0,-3,0,1},
                    {1,1,1,1, 1,1,1, 1,1,1,1},
                    {0,1,0,1, 1,1,1, 1,0,1,0},

                    {1,1,1,1,1,-1,1, 1,1,1,1},
                    {1,1,1,1, 0,0,0,-5,1,1,1},
                    {1,1,0,0, 0,0,0, 0,0,1,1},
            };

    public ItemMapMJDS(Properties p_i48487_1_) {
        super(p_i48487_1_);

        //CommonFunctions.addToEventBus(this);
    }

    //return (ChunkZ, ChunkY, Floor in Z (as 1,2,3,4))
    public static BlockPos getShrinkPosFromRealPos(BlockPos pos)
    {
        return new BlockPos(pos.getZ() >> 4, pos.getY() >> 4, (pos.getY() % 16) >> 2 + 1);
    }

    public static void setOriginToStack(ItemStack stack, BlockPos pos)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.put(ORI_POS, NBTUtil.writeBlockPos(pos));
        stack.setTag(nbt);
    }

    public static BlockPos readOriginFromStack(ItemStack stack)
    {
//        if (stack.getTag() == null)
//        {
//            CompoundNBT nbt = new CompoundNBT();
//            nbt.put(ORI_POS, NBTUtil.writeBlockPos(BlockPos.ZERO));
//            stack.setTag(new CompoundNBT());
//        }
        return NBTUtil.readBlockPos(stack.getOrCreateTag().getCompound(ORI_POS));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void addSpecialDesc(ItemTooltipEvent event)
    {
        PlayerEntity playerEntity = event.getPlayer();

        if (playerEntity != null && event.getItemStack().getItem() instanceof ItemMapMJDS)
        {
            StringBuilder stringBuilder = new StringBuilder();

            ItemStack stack = event.getItemStack();
            BlockPos pinPoint = getShrinkPosFromRealPos(playerEntity.blockPosition());
            BlockPos origin = readOriginFromStack(stack);

            int playerAtX = pinPoint.getX()-origin.getX();
            int playerAtY = pinPoint.getY()-origin.getY();

            //Client does not know nbt, hence ego
//            String playerStr = EgoUtil.getEgo(playerEntity).equals(MJDSDefine.EnumEgo.APHRODITE) ?
//                    CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER_APHRODITE) :
//                    CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER_POPOLON);

            int tickCount = playerEntity.tickCount % TICK_PER_SECOND;

            String playerStr = tickCount >= 10 ?
            CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER_APHRODITE) :
            CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER_POPOLON);

            String pass = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PASS);
            String blank = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_BLANK);
            String boss = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_BOSS);

//            IdlFramework.Log("Translation:%s->%s, playerText = %s",
//                    IDLNBTDef.MAP_MARK_PLAYER_APHRODITE,
//                    CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER_APHRODITE), playerStr);

            boolean hasLamp = false;

            int curY = 0;
            for (int[] row: CASTLE_MAP) {
                int curX = 0;
                for (int grid: row) {
                    if (playerAtY == curY && playerAtX == curX)
                    {
                        stringBuilder.append(playerStr);
                    }
                    else {
                        switch (grid)
                        {
                            case PASS_:
                                stringBuilder.append(pass);
                                break;
                            case BLANK:
                                stringBuilder.append(blank);
                                break;
                            default:
                               if (hasLamp)
                               {
                                   stringBuilder.append(boss);
                               }else {
                                   stringBuilder.append(pass);
                               }
                               break;
                        }
                    }
                    curX++;
                }
                stringBuilder.append(NEWLINE);
                curY++;
            }

            event.getToolTip().add(new StringTextComponent(stringBuilder.toString()));
        }
    }

}
