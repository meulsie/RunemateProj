package com.meulsie.bots.VBWintertodt;

import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if we are in the Wintertodt area
 */

public class InWintertodtArea extends BranchTask {

    private VBWintertodt bot;

    InWintertodtArea(VBWintertodt bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        Execution.delay(100, 5000, 150);
        return bot.getPlayer() != null && Distance.between(bot.getInWintertodtArea(), bot.getPlayer()) < 20;
    }

    @Override
    public TreeTask failureTask() {
        bot.logText("Not in Wintertodt");
        return bot.gotowintertodtarea;
    }

    @Override
    public TreeTask successTask() {
        bot.logText("We are in Wintertodt");
        return bot.ishealthlow;
    }
}
