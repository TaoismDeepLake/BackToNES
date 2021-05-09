package com.deeplake.backtones.villager.merchantTrade;

import com.deeplake.backtones.IdlFramework;
import net.minecraft.util.Tuple;

import java.util.Random;

public class PriceInfo extends Tuple<Integer, Integer>
{
    public PriceInfo(int p_i45810_1_, int p_i45810_2_)
    {
        super(Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_));

        if (p_i45810_2_ < p_i45810_1_)
        {
            IdlFramework.logger.warn("PriceRange({}, {}) invalid, {} smaller than {}", Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_), Integer.valueOf(p_i45810_2_), Integer.valueOf(p_i45810_1_));
        }
    }

    public int getPrice(Random rand)
    {
        return ((Integer)this.getA()).intValue() >= ((Integer)this.getB()).intValue() ? ((Integer)this.getA()).intValue() : ((Integer)this.getA()).intValue() + rand.nextInt(((Integer)this.getB()).intValue() - ((Integer)this.getA()).intValue() + 1);
    }
}