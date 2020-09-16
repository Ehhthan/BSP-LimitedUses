package org.black_ixx.bossshop.addon.limiteduses;

import org.black_ixx.bossshop.core.BSBuy;
import org.black_ixx.bossshop.core.BSShopHolder;
import org.black_ixx.bossshop.core.conditions.BSConditionType;
import org.bukkit.entity.Player;


public class BSConditionTypeUsesCooldown extends BSConditionType {

    private LimitedUsesManager manager;

    public BSConditionTypeUsesCooldown(LimitedUsesManager manager) {
        this.manager = manager;
    }


    @Override
    public boolean dependsOnPlayer() {
        return true;
    }

    @Override
    public String[] createNames() {
        return new String[]{"usescooldown", "usecooldown", "usecd"};
    }

    @Override
    public String[] showStructure() {
        return new String[] { "uses:[string]:cooldown:[string]"};
    }


    @Override
    public void enableType() {
    }

    @Override
    public boolean meetsCondition(BSShopHolder bsShopHolder, BSBuy bsBuy, Player p, String s, String s1) {
        String split[] = s1.split(":");
        if (manager.detectUsedAmount(p, bsBuy.getShop(), bsBuy) >= Long.valueOf(split[1])) {
            if ((manager.detectLastUseDelay(p, bsBuy.getShop(), bsBuy)/1000) >= Long.valueOf(split[3])) {
                manager.setUses(p, bsBuy.getShop(), bsBuy, 0L);
                return true;
            }
            return false;
        }
        return true;
    }

}
