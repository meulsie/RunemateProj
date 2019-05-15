package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.WaitForFight;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are we in combat?
 */
public class IsPlayerInCombat extends BranchTask {

    private ScrubFighter bot;

    public IsPlayerInCombat(ScrubFighter bot){this.bot = bot;}

    @Override
    public boolean validate() {
        return bot.getPlayer() != null && bot.getPlayer().getTarget() != null;
    }

    @Override
    public TreeTask failureTask() {
        return bot.istimerup;
    }

    @Override
    public TreeTask successTask() {
        return bot.waitforfight;
    }
}
