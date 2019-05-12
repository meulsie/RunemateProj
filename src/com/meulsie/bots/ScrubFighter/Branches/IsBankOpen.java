package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.WalkToBank;
import com.meulsie.bots.ScrubFighter.Leafs.WithdrawFood;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Is the bank open?
 */
public class IsBankOpen extends BranchTask {

    private WithdrawFood withdrawfood = new WithdrawFood();
    private WalkToBank walktobank = new WalkToBank();
    private ScrubFighter bot;

    public IsBankOpen(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walktobank;
    }

    @Override
    public TreeTask successTask() {
        return withdrawfood;
    }
}
