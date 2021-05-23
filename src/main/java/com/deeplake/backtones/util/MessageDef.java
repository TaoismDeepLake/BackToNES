package com.deeplake.backtones.util;

import static com.deeplake.backtones.IdlFramework.MOD_ID;
import static com.deeplake.backtones.util.MJDSDefine.ARTEMIS;
import static com.deeplake.backtones.util.MJDSDefine.POPLON;

public class MessageDef {
    public static String DEAL_SUCCESS = MOD_ID + ".msg.deal.success";
    public static String DEAL_FAIL = MOD_ID + ".msg.deal.fail";

    public static String SWAP_EGO = MOD_ID + ".msg.ego.swap.";
    public static String getSwapEgoMsgKey(int resultEgo)
    {
        return SWAP_EGO.concat(String.valueOf(resultEgo));
    }
}
