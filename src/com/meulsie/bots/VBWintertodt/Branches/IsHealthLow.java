package com.meulsie.bots.VBWintertodt.Branches;

import com.meulsie.bots.VBWintertodt.VBWintertodt;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if we have an axe (equipped)
 */
public class IsHealthLow extends BranchTask {

    private VBWintertodt bot;

    public IsHealthLow(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return bot.getPlayer().getHealthGauge().getPercent() < 60;
    }

    @Override
    public TreeTask failureTask() { return bot.isaxeequipped; } //should go to a different branch this is old logic

    @Override
    public TreeTask successTask() {
        return bot.isfoodinventory;
    }
}
