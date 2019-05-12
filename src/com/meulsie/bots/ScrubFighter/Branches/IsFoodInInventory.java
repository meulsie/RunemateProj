package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.EatFood;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Is there food in inventory?
 */
public class IsFoodInInventory extends BranchTask {

    private EatFood eatfood = new EatFood();
    private IsBankOpen isbankopen = new IsBankOpen();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isbankopen;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
