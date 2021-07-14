package com.deeplake.backtones.items;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.IDLNBTDef;
import net.minecraft.client.Minecraft;
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
import static com.deeplake.backtones.util.IDLNBTDef.ORI_POS;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class ItemMapMJDS extends BaseItemIDF {

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

    public static BlockPos readOriginFromStack(ItemStack stack)
    {
        if (stack.getTag() == null)
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.put(ORI_POS, NBTUtil.writeBlockPos(BlockPos.ZERO));
            stack.setTag(new CompoundNBT());
        }
        return NBTUtil.readBlockPos(stack.getTag().getCompound(ORI_POS));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void addSpecialDesc(ItemTooltipEvent event)
    {
        if (event.getItemStack().getItem() instanceof ItemMapMJDS)
        {
            StringBuilder stringBuilder = new StringBuilder();

            ItemStack stack = event.getItemStack();
            BlockPos pinPoint = getShrinkPosFromRealPos(event.getPlayer().blockPosition());
            BlockPos origin = readOriginFromStack(stack);

            int playerAtX = pinPoint.getX()-origin.getX();
            int playerAtY = pinPoint.getY()-origin.getY();

            String player = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PLAYER);
            String pass = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_PASS);
            String blank = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_BLANK);
            String boss = CommonFunctions.GetStringLocalTranslated(IDLNBTDef.MAP_MARK_BOSS);

            boolean hasLamp = false;

            int curY = 0;
            for (int[] row: CASTLE_MAP) {
                int curX = 0;
                for (int grid: row) {
                    if (playerAtY == curY && playerAtX == curX)
                    {
                        stringBuilder.append(player);
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
