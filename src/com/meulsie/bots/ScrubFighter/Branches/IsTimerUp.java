package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.ChooseNewNPC;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Is time up for our selected NPC?
 */
public class IsTimerUp extends BranchTask {

    private ChooseNewNPC choosenewnpc = new ChooseNewNPC();
    private IsLootableNearby islootablenearby = new IsLootableNearby();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return islootablenearby;
    }

    @Override
    public TreeTask successTask() {
        return choosenewnpc;
    }
}
