package com.meulsie.bots.ScrubFighter;

import com.meulsie.bots.ScrubFighter.Branches.IsFoodInInventory;
import com.meulsie.bots.ScrubFighter.Branches.IsPlayerInCombat;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Is health below threshold?

This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsHealthLow extends BranchTask {

    private IsFoodInInventory isfoodininventory = new IsFoodInInventory();
    private IsPlayerInCombat isplayerincombat = new IsPlayerInCombat();

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isplayerincombat;
    }

    @Override
    public TreeTask successTask() {
        return isfoodininventory;
    }
}
