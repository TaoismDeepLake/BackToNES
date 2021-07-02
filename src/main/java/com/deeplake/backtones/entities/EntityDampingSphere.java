package com.deeplake.backtones.entities;

import com.deeplake.backtones.IdlFramework;
import com.deeplake.backtones.util.CommonFunctions;
import com.deeplake.backtones.util.EntityUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.deeplake.backtones.util.CommonDef.CHUNK_MASK;
import static com.deeplake.backtones.util.CommonDef.CHUNK_SIZE;
import static com.deeplake.backtones.util.EntityUtil.ALL;
import static com.deeplake.backtones.util.IDLNBTDef.*;

@Mod.EventBusSubscriber(modid = IdlFramework.MOD_ID)
public class EntityDampingSphere extends Entity {

    float omegaX = 1f;
    float omegaY = 2f;
    float omegaZ = 1.5f;

    float thetaX = 0f;
    float thetaY = 0f;
    float thetaZ = (float) (Math.PI / 3f);

    public class RecordChange{
        public BlockState state = Blocks.AIR.defaultBlockState();
        public BlockPos pos = BlockPos.ZERO;

        public RecordChange(BlockState state, BlockPos pos) {
            this.state = state;
            this.pos = pos;
        }
    }

    List<RecordChange> changes = new ArrayList<>();
    protected Logger logger = LogManager.getLogger();
    //private static final DataParameter<Integer> COUNTER = EntityDataManager.defineId(EntityDampingSphere.class, DataSerializers.INT);

    public EntityDampingSphere(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
        if (!level.isClientSide)
        {
            CommonFunctions.addToEventBus(this);
        }
    }

    public boolean isInDistance(BlockPos pos)
    {
        return (pos.getX() | CHUNK_MASK) == ((int)getX() | CHUNK_MASK)
                && (pos.getZ() | CHUNK_MASK) == ((int)getZ() | CHUNK_MASK);
    }

    //@OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public void onBlockChange(BlockEvent.BreakEvent breakEvent)
    {
        //if (!breakEvent.getPlayer().isCreative())
        {
            BlockPos pos = breakEvent.getPos();
            if (isInDistance(pos))
            {
                changes.add(new RecordChange(breakEvent.getState(), pos));
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide)
        {
            if (CommonFunctions.isSecondTick(level))
            {
                if (EntityUtil.getEntitiesWithinAABBignoreY(level,
                        EntityType.PLAYER,
                        getPosition(0),
                        CHUNK_SIZE,
                        ALL).size() == 0)
                {
                    for (int i = 0; i <= 16; i++)
                    {
                        if (changes.size() > 0)
                        {
                            RecordChange change = changes.get(0);
                            level.setBlock(change.pos, change.state, 3);
                            changes.remove(change);
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }
        else {
            float x = (float) (getX() + Math.sin(thetaX));
            float y = (float) (getY() + Math.sin(thetaY));
            float z = (float) (getZ() + Math.sin(thetaZ));
            thetaX+=omegaX;
            thetaY+=omegaY;
            thetaZ+=omegaZ;

            level.addParticle(ParticleTypes.EFFECT, x, y, z, 0, 0, 0);
        }
    }

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.EntityPlaceEvent placeEvent)
    {
        //set block state won't trigger this, but player's regular action can
        Entity entity = placeEvent.getEntity();
        if (entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())
        {
            return;
        }

        BlockPos pos = placeEvent.getPos();
        if (isInDistance(pos))
        {
            level.destroyBlock(pos, true);
            //placeEvent.setCanceled(true);//wont drop the block
            if (level.isClientSide)
            {
                level.addParticle(ParticleTypes.EXPLOSION, pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, 0, 0, 0);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        //this.entityData.set(COUNTER, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        ListNBT nbtRecordList = p_70037_1_.getList(CHANGE_RECORD, 10);

        for (INBT v: nbtRecordList)
        {
            try
            {
                CompoundNBT compoundNBT = (CompoundNBT) v;
                RecordChange recordChange = new RecordChange(
                        NBTUtil.readBlockState((CompoundNBT) compoundNBT.get(BLOCK_STATE)),
                        NBTUtil.readBlockPos((CompoundNBT) compoundNBT.get(BLOCK_POS))
                );
                changes.add(recordChange);
            }
            catch (Exception e)
            {
                IdlFramework.LogWarning("Cannot resolve NBT:", v.toString());
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        ListNBT nbtRecordList = new ListNBT();

        for (RecordChange v: changes)
        {
            CompoundNBT nbtRecord = new CompoundNBT();
            nbtRecord.put(BLOCK_STATE, NBTUtil.writeBlockState(v.state));
            nbtRecord.put(BLOCK_POS, NBTUtil.writeBlockPos(v.pos));
            nbtRecordList.add(nbtRecord);
        }

        p_213281_1_.put(CHANGE_RECORD, nbtRecordList);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @SubscribeEvent
    public static void onInit(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityDampingSphere)
        {
            CommonFunctions.addToEventBus(event.getEntity());
        }
    }
}
