package com.meulsie.bots.ScrubFighter.Branches;

import com.meulsie.bots.ScrubFighter.Leafs.AttackTarget;
import com.meulsie.bots.ScrubFighter.Leafs.WalkToFightArea;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are we in the right area for our chosen NPC?
 */
public class IsPlayerInFightArea extends BranchTask {

    private AttackTarget attacktarget = new AttackTarget();
    private WalkToFightArea walktofightarea = new WalkToFightArea();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walktofightarea;
    }

    @Override
    public TreeTask successTask() {
        return attacktarget;
    }
}
