package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.WalkToBank;
import com.meulsie.bots.ScrubFighter.Leafs.WithdrawFood;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Is the bank open?
 */
public class IsBankOpen extends BranchTask {

    private ScrubFighter bot;

    public IsBankOpen(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return bot.walktobank;
    }

    @Override
    public TreeTask successTask() {
        return bot.withdrawfood;
    }
}
