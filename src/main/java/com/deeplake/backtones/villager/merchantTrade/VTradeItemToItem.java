package com.deeplake.backtones.villager.merchantTrade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

import javax.annotation.Nullable;
import java.util.Random;

//revived from 1.12
public class VTradeItemToItem implements VillagerTrades.ITrade {

    public static final PriceInfo PRICE_ONE = new PriceInfo(1,1);

    public ItemStack buyingItemStack = ItemStack.EMPTY;
    public PriceInfo buyingPriceInfo;

    public ItemStack buyingItemStack2 = ItemStack.EMPTY;
    public PriceInfo buyingPriceInfo2;

    public ItemStack sellingItemstack = ItemStack.EMPTY;
    public PriceInfo sellingPriceInfo;

    @Nullable
    public MerchantOffer getOffer(Entity buyer, Random random) {
        ItemStack stack1 = buyingItemStack.copy();
        ItemStack stack2 = buyingItemStack2.copy();
        ItemStack stack3 = sellingItemstack.copy();

        stack1.setCount(buyingPriceInfo.getPrice(random));
        stack2.setCount(buyingPriceInfo2.getPrice(random));
        stack3.setCount(sellingPriceInfo.getPrice(random));

        return new MerchantOffer(
                stack1,
                stack2,
                stack3,
                7, 7, 1);
    }

    public enum CostType {
        NONE,
        EMRALD,
        DIAMOND,
        GOLD_NUGGET,
        GOLD_INGOT,
    }

    public static ItemStack getStackFromType(CostType type)
    {
        switch (type)
        {
            case EMRALD:
                return new ItemStack(Items.EMERALD);
            case DIAMOND:
                return new ItemStack(Items.DIAMOND);
            case GOLD_NUGGET:
                return new ItemStack(Items.GOLD_NUGGET);
            case GOLD_INGOT:
                return new ItemStack(Items.GOLD_INGOT);
            default:
                return ItemStack.EMPTY;
        }
    }


    //Item Version
    public VTradeItemToItem(Item costA, PriceInfo priceInfo,
                            Item costB, PriceInfo priceInfo2,
                            Item result, PriceInfo priceInfo3)
    {
        this( new ItemStack(costA), priceInfo, new ItemStack(costB), priceInfo2, new ItemStack(result), priceInfo3);
    }

    //Stack Version
    public VTradeItemToItem(ItemStack buyingItemStack, PriceInfo buyingPriceInfo, @Nullable ItemStack buyingItemStack2, @Nullable PriceInfo buyingPriceInfo2, ItemStack sellingItemstack, PriceInfo sellingPriceInfo) {
        this.buyingItemStack = buyingItemStack;
        this.buyingPriceInfo = buyingPriceInfo;
        this.buyingItemStack2 = buyingItemStack2 == null ? ItemStack.EMPTY : buyingItemStack2;
        this.buyingPriceInfo2 = buyingPriceInfo2 == null ? PRICE_ONE : buyingPriceInfo2;
        this.sellingItemstack = sellingItemstack;
        this.sellingPriceInfo = sellingPriceInfo;
    }

    //Stack X-Z Version
    public VTradeItemToItem(ItemStack buyingItemStack, PriceInfo buyingPriceInfo, ItemStack sellingItemstack, PriceInfo sellingPriceInfo) {
        this(buyingItemStack,buyingPriceInfo, ItemStack.EMPTY, PRICE_ONE, sellingItemstack, sellingPriceInfo);
    }

    //Stack X1-Z1 Item Version
    public VTradeItemToItem(Item costStack, Item resultStack) {
        this(new ItemStack(costStack), PRICE_ONE, new ItemStack(resultStack), PRICE_ONE);
    }

    //Stack X1-Z1 Version
    public VTradeItemToItem(ItemStack costStack, ItemStack resultStack) {
        this(costStack, PRICE_ONE, resultStack, PRICE_ONE);
    }

    public VTradeItemToItem(CostType costType, PriceInfo costPriceInfo, ItemStack resultStack) {
        this(costType, costPriceInfo, resultStack, PRICE_ONE);
    }

    public VTradeItemToItem(CostType costType, ItemStack resultStack, PriceInfo resultPriceInfo) {
        this(costType, PRICE_ONE, resultStack, resultPriceInfo);
    }

    public VTradeItemToItem(CostType costType, ItemStack resultStack, int count) {
        this(costType, PRICE_ONE, resultStack, new PriceInfo(count, count));
    }

    public VTradeItemToItem(CostType costType, PriceInfo costPriceInfo, ItemStack resultStack, PriceInfo sellingPriceInfo) {
        this(getStackFromType(costType), costPriceInfo, resultStack, sellingPriceInfo);
    }

//    @Override
//    public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
//        int countA = buyingPriceInfo.getPrice(random);
//        int countB = buyingPriceInfo2 == null ? 0 : buyingPriceInfo2.getPrice(random);
//        int countResult = sellingPriceInfo.getPrice(random);
//        recipeList.add(
//                new MerchantRecipe(
//                        CommonFunctions.copyAndSetCount(buyingItemStack, countA),
//                        CommonFunctions.copyAndSetCount(buyingItemStack2, countB),
//                        CommonFunctions.copyAndSetCount(sellingItemstack, countResult)
//                )
//        );
//    }

}
