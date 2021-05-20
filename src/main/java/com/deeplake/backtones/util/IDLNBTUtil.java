package com.deeplake.backtones.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.deeplake.backtones.util.IDLNBT.*;
import static com.deeplake.backtones.util.IDLNBTDef.*;

//on a server, strlen 65000 is ok, but 66000 will crash
public class IDLNBTUtil {
	public static CompoundNBT getNBT(ItemStack stack)
	{
		CompoundNBT nbt = stack.getTag();
		if (nbt == null)
		{
			nbt = new CompoundNBT();
			stack.setTag(nbt);
		}
		return nbt;
	}
	
	public static CompoundNBT getNBT(Entity entity) {
		CompoundNBT nbt = entity.getPersistentData();
	    return nbt;
	}
	
	
	public static CompoundNBT getNBT(CompoundNBT tag) {
	    if(tag == null) {
	      return new CompoundNBT();
	    }

	    return tag;
	  }
	
	//writeEntityToNBT
	//readEntityFromNBT
	
	@Nullable
	public static boolean StackHasKey(ItemStack stack, String key) {
		return !(stack.isEmpty() || !getNBT(stack).contains(key));
	}

	//Boolean
	public static boolean SetBoolean(ItemStack stack, String key, boolean value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putBoolean(key, value);
		return true;
	}
	
	public static boolean GetBoolean(ItemStack stack, String key, boolean defaultVal)
	{	
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getBoolean(key);
		}		
		else
		{
			return defaultVal;
		}
	}
	
	public static boolean GetBoolean(ItemStack stack, String key)
	{	
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getBoolean(key);
		}		
		else
		{
			return false;
		}
	}
	//get with default val
	public static boolean GetBooleanDF(ItemStack stack, String key, boolean defaultVal)
	{	
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getBoolean(key);
		}		
		else
		{
			return defaultVal;
		}
	}

	//Double
	public static boolean SetDouble(ItemStack stack, String key, double value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putDouble(key, value);
		return true;
	}

	public static double GetDouble(ItemStack stack, String key, double defaultVal)
	{
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getDouble(key);
		}
		else
		{
			return defaultVal;
		}
	}

	//Integer
	public static boolean SetLong(ItemStack stack, String key, long value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putLong(key, value);
		return true;
	}
	public static boolean SetInt(ItemStack stack, String key, int value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putInt(key, value);
		return true;
	}
	public static boolean SetIntOptimized(ItemStack stack, String key, int value)
	{
		CompoundNBT nbt = getNBT(stack);
		if (nbt.getInt(key) != value)
		{
			nbt.putInt(key, value);
		}
		return true;
	}
	public static boolean SetInt(Entity entity, String key, int value)
	{
		CompoundNBT nbt = getNBT(entity);
		nbt.putInt(key, value);
		return true;
	}
	public static boolean SetIntAuto(Entity entity, String key, int value)
	{
		if (entity instanceof PlayerEntity)
		{
			setPlayerIdeallandTagSafe((PlayerEntity) entity, key, value);
			return true;
		}
		CompoundNBT nbt = getNBT(entity);
		nbt.putInt(key, value);
		return true;
	}

	public static boolean AddIntAuto(Entity entity, String key, int value)
	{
		int oldVal = GetIntAuto(entity, key, 0);
		SetIntAuto(entity, key, value + oldVal);
		return true;
	}

	public static int GetInt(Entity entity, String key, int defaultVal)
	{
		if (EntityHasKey(entity, key))
		{
			CompoundNBT nbt = getNBT(entity);
			return nbt.getInt(key);
		}
		else
		{
			return defaultVal;
		}
	}

	public static int GetIntAuto(Entity entity, String key, int defaultVal)
	{
		if (entity instanceof PlayerEntity)
		{
			return getPlayerIdeallandIntSafe((PlayerEntity) entity, key);
		}

		if (EntityHasKey(entity, key))
		{
			CompoundNBT nbt = getNBT(entity);
			return nbt.getInt(key);
		}
		else
		{
			return defaultVal;
		}
	}

	public static int GetInt(ItemStack stack, String key, int defaultVal)
	{
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getInt(key);
		}		
		else
		{
			return defaultVal;
		}
	}

	public static long GetLong(ItemStack stack, String key, int defaultVal)
	{
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getLong(key);
		}
		else
		{
			return defaultVal;
		}
	}


	public static int GetInt(ItemStack stack, String key)
	{
		return GetInt(stack, key, 0);
	}
	
	//String
	public static String GetString(ItemStack stack, String key, String defaultVal)
	{
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getString(key);
		}		
		else
		{
			return defaultVal;
		}
	}
	
	public static boolean SetString(ItemStack stack, String key, String value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putString(key, value);

		return true;
	}

	
	//entity
	@Nullable
	public static boolean EntityHasKey(Entity entity, String key)
	{
		return getNBT(entity).contains(key);
	}
	
	//Boolean
	public static boolean GetBoolean(Entity entity, String key, boolean defaultVal)
	{	
		if (EntityHasKey(entity, key))
		{
			CompoundNBT nbt = getNBT(entity);
			return nbt.getBoolean(key);
		}		
		else
		{
			return defaultVal;
		}
	}
	
	public static boolean SetBoolean(Entity stack, String key, boolean value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putBoolean(key, value);
		return true;
	}

	public static boolean SetString(Entity stack, String key, String value)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putString(key, value);
		return true;
	}

	public static int[] GetIntArray(ItemStack stack, String key)
	{
		if (StackHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getIntArray(key);
		}
		else
		{
			return new int[0];
		}
	}

	public static int[] GetIntArray(LivingEntity stack, String key)
	{
		if (EntityHasKey(stack, key))
		{
			CompoundNBT nbt = getNBT(stack);
			return nbt.getIntArray(key);
		}
		else
		{
			return new int[0];
		}
	}

	public static void SetIntArray(ItemStack stack, String key, int[] array)
	{
		CompoundNBT nbt = getNBT(stack);
		nbt.putIntArray(key, array);
	}

	public static void SetGuaEnhanceFree(ItemStack stack, int val)
	{
		SetInt(stack, GUA_FREE_SOCKET, val);
	}

	public static boolean GetIsLearned(PlayerEntity player, int skillID)
	{
		int[] learnt = getPlayerIdeallandIntArraySafe(player, STARTER_KIT_VERSION_TAG);
		if (Arrays.binarySearch(learnt, skillID) >= 0)
		{
			return true;
		}
		return false;
	}

	public static void SetIsLearned(PlayerEntity player, int skillID, boolean val)
	{
		int[] learnt = getPlayerIdeallandIntArraySafe(player, LEARNING_DONE);
		int oldIndex = Arrays.binarySearch(learnt, skillID);
		if (oldIndex >= 0)
		{
			if (val)
			{
				return;
			}else {
				//todo: remove it

			}
		}else {
			if (val)
			{
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int oldID:
						learnt
					 ) {
					list.add(oldID);
				}
				list.add(skillID);
				Collections.sort(list);

				int[] newLearnt = list.stream().mapToInt(Integer::valueOf).toArray();
				setPlayerIdeallandTagSafe(player, LEARNING_DONE, newLearnt);
			}else {
				return;
			}
		}
	}
	//--------------------------------------------



	public static BlockPos getMarkedPos(ItemStack stack)
	{
		CompoundNBT NBT = IDLNBTUtil.getNBT(stack);
		return new BlockPos(NBT.getDouble(ANCHOR_X), NBT.getDouble(ANCHOR_Y), NBT.getDouble(ANCHOR_Z));
	}

	public static BlockPos getMarkedPos2(ItemStack stack)
	{
		CompoundNBT NBT = IDLNBTUtil.getNBT(stack);
		return new BlockPos(NBT.getDouble(ANCHOR_X_2), NBT.getDouble(ANCHOR_Y_2), NBT.getDouble(ANCHOR_Z_2));
	}

	public static void markPosToStack(ItemStack stack, BlockPos pos)
	{
		IDLNBTUtil.SetBoolean(stack, ANCHOR_READY, true);
		IDLNBTUtil.SetDouble(stack, ANCHOR_X, pos.getX());
		IDLNBTUtil.SetDouble(stack, ANCHOR_Y, pos.getY());
		IDLNBTUtil.SetDouble(stack, ANCHOR_Z, pos.getZ());
	}

	public static void markPosToStack2(ItemStack stack, BlockPos pos)
	{
		IDLNBTUtil.SetBoolean(stack, ANCHOR_READY_2, true);
		IDLNBTUtil.SetDouble(stack, ANCHOR_X_2, pos.getX());
		IDLNBTUtil.SetDouble(stack, ANCHOR_Y_2, pos.getY());
		IDLNBTUtil.SetDouble(stack, ANCHOR_Z_2, pos.getZ());
	}
}
