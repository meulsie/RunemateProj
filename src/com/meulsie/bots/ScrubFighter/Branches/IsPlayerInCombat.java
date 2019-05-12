package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.WaitForFight;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are we in combat?
 */
public class IsPlayerInCombat extends BranchTask {

    private WaitForFight waitforfight = new WaitForFight();
    private IsTimerUp istimerup = new IsTimerUp();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return istimerup;
    }

    @Override
    public TreeTask successTask() {
        return waitforfight;
    }
}
