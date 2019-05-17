package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Is health below threshold?

This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsHealthLow extends BranchTask {

    private ScrubFighter bot;

    public IsHealthLow(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return Health.getCurrentPercent() < 95;
    }

    @Override
    public TreeTask failureTask() {
        return bot.isplayerincombat;
    }

    @Override
    public TreeTask successTask() {
        return bot.eatfood;
    }
}
