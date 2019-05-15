package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.AttackTarget;
import com.meulsie.bots.ScrubFighter.Leafs.WalkToFightArea;
import com.meulsie.bots.ScrubFighter.ScrubFighter;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are we in the right area for our chosen NPC?
 */
public class IsPlayerInFightArea extends BranchTask {

    private ScrubFighter bot;

    public IsPlayerInFightArea(ScrubFighter bot) {
        this.bot = bot;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return bot.walktofightarea;
    }

    @Override
    public TreeTask successTask() {
        return bot.attacktarget;
    }
}
